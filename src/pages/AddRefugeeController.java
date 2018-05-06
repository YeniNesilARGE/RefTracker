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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tableObjects.RefugeeTable;

public class AddRefugeeController {

    @FXML
    TextField nText;

    @FXML
    TextField snText;

    @FXML
    TextField raceText;

    @FXML
    TextField natiText;

    @FXML
    TextField socialText;

    @FXML
    RadioButton maleRadio;

    @FXML
    RadioButton famaleRadio;

    @FXML
    Button addRefuge;

    Stage s;
    Stage stageRefugee;
    RefugeeController rc;

    public Stage getS() {
        return s;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    public Stage getStageRefugee() {
        return stageRefugee;
    }

    public void setStageRefugee(Stage stageRefugee) {
        this.stageRefugee = stageRefugee;
    }

    public RefugeeController getRc() {
        return rc;
    }

    public void setRc(RefugeeController rc) {
        this.rc = rc;
    }

    int cId;
    int tId;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    @FXML
    private void addClicked(MouseEvent event) {

        LoginController lc = new LoginController();

        Refugee r = new Refugee();
        r.setName(nText.getText());
        r.setSurname(snText.getText());
        r.setRace(raceText.getText());
        r.setNationality(natiText.getText());
        r.setSocialId(socialText.getText());
        r.setTentId(getcId());
        r.setCampId(gettId());
        r.setIsAlive(1);
        r.setIsStay(1);
        r.setEverTransport(0);
        if (famaleRadio.isSelected()) {
            r.setGender(1);
        } else {
            r.setGender(0);
        }
        EntityManager em = LoginController.dbConnection.newEntityManager();
        em.persist(r);
        em.getTransaction().commit();
        em.close();
        s.hide();

        TentsController tc = new TentsController();
        tc.findClicked(event);

    }

}


/*LoginController lg = new LoginController();
      lg.sceneTransition("Login.fxml");*/
