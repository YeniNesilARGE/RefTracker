package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static pages.LoginController.dbConnection;
import tableObjects.CampsTable;
import tableObjects.RefugeeTable;

public class AddTentController implements Initializable {

    Stage s;
    private int campID;

    public Stage getS() {
        return s;
    }

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    @FXML
    private ComboBox tentType;

    @FXML
    private TextArea tentDesc;

    @FXML
    private Button addTentButton;

    @FXML
    private void addTent(MouseEvent event) {
        Tent t = new Tent();
        t.setDescription(tentDesc.getText());
        //t.setTypeId();
        t.setCampId(campID);
        
        //find tent type by name
        EntityManager e = LoginController.dbConnection.newEntityManager();
        TypedQuery<TentType> q = e.createQuery("SELECT t FROM TentType t WHERE t.type='" + tentType.getSelectionModel().getSelectedItem().toString() + "'", TentType.class);
        List<TentType> tl = q.getResultList();
        if (!(tl.isEmpty())) {
            t.setTypeId(tl.get(0).getId());
        } else {
            t.setTypeId(-1);
        }
        e.close();

        EntityManager em = LoginController.dbConnection.newEntityManager();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        s.hide();
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Tents.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        TentsController tc = Loader.getController();
        tc.setCampID(campID);
        LoginController lc = new LoginController();
        lc.sceneTransition("Tents.fxml");
    }

    private ObservableList<String> tentTypes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tentTypes = FXCollections.observableArrayList();
        EntityManager em = dbConnection.newEntityManager();
        TypedQuery<TentType> tq = em.createQuery("SELECT tt FROM TentType tt", TentType.class);
        List<TentType> ttList = tq.getResultList();
        for (int i = 0; i < ttList.size(); i++) {
            TentType t = ttList.get(i);
            tentTypes.add(t.getType());
        }
        em.close();
        tentType.setItems(tentTypes);
    }
}
