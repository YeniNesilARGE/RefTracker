/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import DatabaseClasses.CampSite;
import DatabaseClasses.CampType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Furkan
 */
public class EditCampController implements Initializable {
    
    Stage s;
    
    CampSite cs;
    
    public void setS(Stage s) {
        this.s = s;
    }
    public void setCampSite(CampSite cs){
        this.cs = cs;
        campName.setText(cs.getName());
        campLoc.setText(cs.getLocation());
    }
    
    @FXML
    private TextField campLoc;
    
    @FXML
    private TextField campName;
    
    @FXML
    private ComboBox campType;
    
    private EntityManager em;
    
    @FXML
    private void editCamp(MouseEvent e){
        em = LoginController.dbConnection.newEntityManager();
        CampSite c = em.find(CampSite.class, cs.getId());
        
        
        EntityManager em2 = LoginController.dbConnection.newEntityManager();
        TypedQuery<CampType> q1 = em2.createQuery("SELECT t FROM CampType t WHERE t.name ='" + campType.getValue().toString() + "'", CampType.class);
        List<CampType> l = q1.getResultList();
        
        c.setCampType(l.get(0).getId());
        c.setLocation(campLoc.getText());
        c.setName(campName.getText());
        c.setRequirement("requirement");
        em2.close();
        
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        s.hide();
        LoginController lc = new LoginController();
        lc.sceneTransition("Camps.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        // Starting connection
        em = LoginController.dbConnection.newEntityManager();
        // Filling combobox
        TypedQuery<CampType> q1 = em.createQuery("SELECT ct FROM CampType ct", CampType.class);
        List<CampType> l = q1.getResultList();
        ObservableList<String> ol = FXCollections.observableArrayList();
        for (CampType ct : l) {
            ol.add(ct.getName());
        }
        campType.setItems(ol);
        em.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }    
    
}
