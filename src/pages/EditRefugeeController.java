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
import javafx.scene.control.Label;
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

public class EditRefugeeController {

    void init() {
        nText.setText(r.getName());
        snText.setText(r.getSurname());
        raceText.setText(r.getRace());
        natiText.setText(r.getNationality());
        if (r.getGender() == 0) {
            getMaleRadio().selectedProperty();
        } else {
            getFamaleRadio().selectedProperty();
        }
        socialText.setText(r.getSocialId());
    }

    Refugee r;

    public Refugee getR() {
        return r;
    }

    public void setR(Refugee r) {
        this.r = r;
    }

    @FXML
    TextField nText;

    @FXML
    Label socialLbl;

    @FXML
    TextField snText;

    public TextField getnText() {
        return nText;
    }

    public void setnText(TextField nText) {
        this.nText = nText;
    }

    public TextField getSnText() {
        return snText;
    }

    public void setSnText(TextField snText) {
        this.snText = snText;
    }

    public TextField getRaceText() {
        return raceText;
    }

    public void setRaceText(TextField raceText) {
        this.raceText = raceText;
    }

    public TextField getNatiText() {
        return natiText;
    }

    public void setNatiText(TextField natiText) {
        this.natiText = natiText;
    }

    public TextField getSocialText() {
        return socialText;
    }

    public void setSocialText(TextField socialText) {
        this.socialText = socialText;
    }

    public RadioButton getMaleRadio() {
        return maleRadio;
    }

    public void setMaleRadio(RadioButton maleRadio) {
        this.maleRadio = maleRadio;
    }

    public RadioButton getFamaleRadio() {
        return famaleRadio;
    }

    public void setFamaleRadio(RadioButton famaleRadio) {
        this.famaleRadio = famaleRadio;
    }

    public Button getAddRefuge() {
        return addRefuge;
    }

    public void setAddRefuge(Button addRefuge) {
        this.addRefuge = addRefuge;
    }

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

        EntityManager em = LoginController.dbConnection.newEntityManager();
        Refugee rr = em.find(Refugee.class, getR().getId());

        rr.setName(nText.getText());
        rr.setSurname(snText.getText());
        rr.setRace(raceText.getText());
        rr.setNationality(natiText.getText());

        EntityManager em2 = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q2 = em2.createQuery("SELECT r FROM Refugee r WHERE r.socialId ='" + socialText.getText() + "'", Refugee.class);
        List<Refugee> rL = q2.getResultList();
        em2.close();
        if ( (!(rL.isEmpty()) && (rL.get(0).getSocialId().equals(socialText.getText()))) || rL.isEmpty() ) {
            rr.setTentId(getR().getTentId());
            rr.setCampId(getR().getCampId());
            rr.setIsAlive(1);
            rr.setIsStay(1);
            rr.setSocialId(socialText.getText());
            rr.setEverTransport(0);
            if (famaleRadio.isSelected()) {
                rr.setGender(1);
            } else {
                rr.setGender(0);
            }
            em.persist(rr);
            em.getTransaction().commit();
            em.close();
            s.hide();
            TentsController tc = new TentsController();
            tc.setCampID(getR().getCampId());
            tc.setTentId2(getR().getTentId());
            tc.detailClicked(event);
        } else {
            socialLbl.setText("Used by someone else");
        }
    }

}


/*LoginController lg = new LoginController();
      lg.sceneTransition("Login.fxml");*/
