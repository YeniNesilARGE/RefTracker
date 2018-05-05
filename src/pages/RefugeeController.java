package pages;

import DatabaseClasses.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.TypedQuery;
import tableObjects.RefugeeTable;

public class RefugeeController {

    @FXML
    private TableView<RefugeeTable> refugeesTable = new TableView<>();
    @FXML
    private Button addRefugeeButton;
    @FXML
    private Button refugeeDetailButton;
    
    
    int campId;
    
    private ObservableList<RefugeeTable> refugees = FXCollections.observableArrayList();

    public ObservableList<RefugeeTable> getRefugees() {
        return refugees;
    }

    public void setRefugees(ObservableList<RefugeeTable> refugees) {
        this.refugees = refugees;
    }
    
    

    
    
    
    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }
    
    
    
    //CADIR ID'SI GELIR VE SIGINMACILAR LISTELENIR
    @FXML
    private void addClicked(MouseEvent event) {
        //add refugee
    }

    @FXML
    private void detailClicked(MouseEvent event) {
        //refugee detail
    }

    public void setColumns() {
        
        TableColumn name = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn nationality = new TableColumn("Nationality");
        TableColumn campName = new TableColumn("Camp Name");
        TableColumn tentName = new TableColumn("Tent Name");
        TableColumn gender = new TableColumn("Gender");
        TableColumn alive = new TableColumn("Alive");
        
        name.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("surName"));
        nationality.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("nation"));
        campName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("campName"));
        tentName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("tentName"));
        gender.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("gender"));
        alive.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("alive"));
        
        
        
        refugeesTable.getColumns().clear();
        refugeesTable.getColumns ().addAll(name, lastName, nationality,campName,tentName,gender,alive);
        refugeesTable.setItems(refugees);
        
    }

    

    

   public String findCampName(Refugee r) {
        int campId = r.getCampId();
        TypedQuery<CampSite> q1 = LoginController.dbConnection.getEm().createQuery("SELECT c FROM CampSite c WHERE c.id = '" + campId + "'", CampSite.class);
        CampSite c = q1.getSingleResult();
        return c.getLocation()+"/"+c.getName();
    }

    public String findTentName(Refugee r) {
        int tentId = r.getTentId();
        TypedQuery<Tent> q1 = LoginController.dbConnection.getEm().createQuery("SELECT t FROM Tent t WHERE t.id ='" + tentId + "'", Tent.class);
        Tent t = q1.getSingleResult();
        return t.getDescription();
    }

    public String findGender(Refugee r) {
        if(r.getGender()==0)
            return "Male";
        else
            return "Female";
    }

    public String findIsAlive(Refugee r) {
        if(r.getIsAlive()==0)
            return "Dead";
        else
            return "Alive";
    }

}


/*LoginController lg = new LoginController();
      lg.sceneTransition("Login.fxml");*/
