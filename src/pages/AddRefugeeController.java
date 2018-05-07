package pages;

import DatabaseClasses.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;

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
        r.setTentId(gettId());
        r.setCampId(getcId());
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
        tc.setCampID(getcId());
        tc.setTentId2(gettId());
        tc.detailClicked(event);

    }

}


/*LoginController lg = new LoginController();
      lg.sceneTransition("Login.fxml");*/
