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

public class EditTentController implements Initializable {

    Stage s;
    public static int tentID;
    public static int campID;

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public void setTentID(int tentID) {
        this.tentID = tentID;
    }

    public Stage getS() {
        return s;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    @FXML
    private ComboBox tentType = new ComboBox();

    @FXML
    private TextArea tentDesc = new TextArea();

    @FXML
    private Button editTentButton;

    @FXML
    private void editTent(MouseEvent event) {
        EntityManager ee = LoginController.dbConnection.newEntityManager();
        Tent t = ee.find(Tent.class, tentID);
        t.setDescription(tentDesc.getText());

        //find tent type by name
        EntityManager e = LoginController.dbConnection.newEntityManager();
        TypedQuery<TentType> q = e.createQuery("SELECT t FROM TentType t WHERE t.type='" + tentType.getSelectionModel().getSelectedItem().toString() + "'", TentType.class);
        TentType tl = q.getSingleResult();
        t.setTypeId(tl.getId());
        e.close();

        //update
        ee.persist(t);
        ee.getTransaction().commit();
        ee.close();
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
        //find the tent
        Tent t = new Tent();
        EntityManager e = dbConnection.newEntityManager();
        TypedQuery<Tent> te = e.createQuery("SELECT t FROM Tent t WHERE t.id='" + tentID + "'", Tent.class);
        t = te.getSingleResult();
        e.close();
        //description fill
        tentDesc.setText(t.getDescription());

        //find tent types
        tentTypes = FXCollections.observableArrayList();
        EntityManager em = dbConnection.newEntityManager();
        TypedQuery<TentType> tq = em.createQuery("SELECT tt FROM TentType tt", TentType.class);
        List<TentType> ttList = tq.getResultList();
        for (int i = 0; i < ttList.size(); i++) {
            TentType tt = ttList.get(i);
            tentTypes.add(tt.getType());
        }
        em.close();
        tentType.setItems(tentTypes);

        //find tent type by id
        EntityManager ee = LoginController.dbConnection.newEntityManager();
        TypedQuery<TentType> qq = ee.createQuery("SELECT t FROM TentType t WHERE t.id'" + t.getTypeId() + "'", TentType.class);
        List<TentType> tl = qq.getResultList();
        if (!(tl.isEmpty())) {
            tentType.getSelectionModel().select(tl.get(0).getType());
        }
        ee.close();

    }
}
