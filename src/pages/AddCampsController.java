package pages;

import DatabaseClasses.*;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static pages.LoginController.dbConnection;
import tableObjects.CampsTable;
import tableObjects.RefugeeTable;

public class AddCampsController {
    
    Stage s;
    
    public Stage getS() {
        return s;
    }
    
    public void setS(Stage s) {
        this.s = s;
    }
    
    @FXML
    private TextField campLoc;
    
    @FXML
    private TextField campName;
    
    @FXML
    private TextField campType;
    
    @FXML
    private void addCamp(MouseEvent event) {
        CampSite c = new CampSite();
        c.setCampType(Integer.parseInt(campType.getText()));
        c.setLocation(campLoc.getText());
        c.setName(campName.getText());
        c.setRequirement("requirement");
        
        EntityManager em = LoginController.dbConnection.newEntityManager();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        s.close();
        LoginController lc = new LoginController();
        lc.sceneTransition("Camps.fxml");
    }
}
