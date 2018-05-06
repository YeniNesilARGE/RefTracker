package pages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.TypedQuery;
import tableObjects.CampsTable;
import DatabaseClasses.CampSite;
import java.util.List;

public class CampsController implements Initializable {
   @FXML
   private TableView campsTable;
   @FXML
   private Button addCampButton;
   @FXML
   private Button findCampButton;
   @FXML
   private Button campDetailButton;
   
   private ObservableList<CampsTable> camps;
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
       campsTable = new TableView<>();
       camps = FXCollections.observableArrayList();
       
       TypedQuery<CampSite> tq = LoginController.dbConnection.getEm().createQuery("SELECT cs FROM CampSite cs", CampSite.class);
       List<CampSite> csList = tq.getResultList();
       for (int i = 0; i < csList.size(); i++) {
           CampSite c = csList.get(i);
           camps.add(new CampsTable(c.getName(), c.getLocation()));
       }
       setColumns();
       campsTable.setItems(camps);
   }
   @FXML
   private void addClicked(MouseEvent event){
      //add camp
   }

   @FXML
   private void findClicked(MouseEvent event){
      //find camp
   }

   @FXML
   private void detailClicked(MouseEvent event){
      //camp detail
   }
   public void setColumns() {
        TableColumn name = new TableColumn("Name");
        TableColumn location = new TableColumn("Location");
        
        name.setCellValueFactory(new PropertyValueFactory<CampsTable,String>("name"));
        location.setCellValueFactory(new PropertyValueFactory<CampsTable,String>("location"));
        
        campsTable.getColumns().clear();
        campsTable.getColumns().addAll(name, location);
    }
}