package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
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
    int tentId;
    
    Stage refugeeStage;

    public Stage getRefugeeStage() {
        return refugeeStage;
    }

    public void setRefugeeStage(Stage refugeeStage) {
        this.refugeeStage = refugeeStage;
    }

    public int getTentId() {
        return tentId;
    }

    public void setTentId(int tentId) {
        this.tentId = tentId;
    }
    
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
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("AddRefugee.fxml"));
            try{
                Loader.load();
            }catch(IOException ex){
                System.out.println(ex);
            }
            AddRefugeeController arc = Loader.getController();
            arc.setcId(campId);
            arc.settId(tentId);
            arc.setRc(this);
            Parent p = Loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            arc.setS(s);
            s.show();
            refugeeStage.close();
    }

    @FXML
    private void detailClicked(MouseEvent event) {
        RefugeeTable rt = refugeesTable.getSelectionModel().getSelectedItem();
        System.out.println(rt.getName()+ "--" + rt.getSurName());
    }

    @FXML
    private void editRef(MouseEvent event) {
        
        RefugeeTable rt = refugeesTable.getSelectionModel().getSelectedItem();
        System.out.println(rt.getName()+ "--" + rt.getSurName());
        String socialId = rt.getSocialId(); 
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.socialId "
                + "='" + socialId + "'" , Refugee.class);
        Refugee l = q1.getSingleResult();
        em.close();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("EditRefugee.fxml"));
            try{
                Loader.load();
            }catch(IOException ex){
                System.out.println(ex);
            }
            EditRefugeeController erc = Loader.getController();
            erc.setR(l);
            erc.setRc(this);
            Parent p = Loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            erc.setS(s);
            s.show();
            refugeeStage.hide();
    }
    
    
    public void setColumns() {
        
        TableColumn name = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn nationality = new TableColumn("Nationality");
        TableColumn campName = new TableColumn("Camp Name");
        TableColumn tentName = new TableColumn("Tent Name");
        TableColumn socialId = new TableColumn("Social ID");
        TableColumn gender = new TableColumn("Gender");
        TableColumn alive = new TableColumn("Alive");
        
        name.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("surName"));
        nationality.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("nation"));
        campName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("campName"));
        tentName.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("tentName"));
        socialId.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("socialId"));
        gender.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("gender"));
        alive.setCellValueFactory(new PropertyValueFactory<RefugeeTable,String>("alive"));
        
        
        refugeesTable.getColumns().clear();
        refugeesTable.getColumns ().addAll(name, lastName, nationality,campName,tentName,socialId,gender,alive);
        refugeesTable.setItems(refugees);
    }
   public String findCampName(Refugee r) {
        int campId = r.getCampId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<CampSite> q1 = em.createQuery("SELECT c FROM CampSite c WHERE c.id = '" + campId + "'", CampSite.class);
        CampSite c = q1.getSingleResult();
        em.close();
        return c.getLocation()+"/"+c.getName();
    }
    public String findTentName(Refugee r) {
        int tentId = r.getTentId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Tent> q1 = em.createQuery("SELECT t FROM Tent t WHERE t.id ='" + tentId + "'", Tent.class);
        Tent t = q1.getSingleResult();
        em.close();
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
