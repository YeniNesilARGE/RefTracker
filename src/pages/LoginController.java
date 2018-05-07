package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import tableObjects.CampsTable;
import tableObjects.TentTable;

public class LoginController {

    class conDB extends Thread {

        int son = 0;

        public void run() {
            if (son == 0) {
                dbConnection.connDb();
                isConnected = true;
                label.setText("Connected to DataBase..");
                System.out.println("thread db ye bağlandı");
                son++;
            }
        }
    }

    @FXML
    private Button button;
    @FXML
    private TextField uId;
    @FXML
    private TextField uPass;
    @FXML
    private Label label;

    static DatabaseConnection dbConnection = new DatabaseConnection();
    static conDB connectDataB;
    static boolean isConnected = false;

    @FXML
    private void mouseClicked(MouseEvent event) {
        try {
            //connectDataB = new conDB();
            //connectDataB.start();
            dbConnection.connDb();
            //if (isConnected) {
            EntityManager em = dbConnection.newEntityManager();
            TypedQuery<Users> q1 = em.createQuery("SELECT u FROM Users u", Users.class);
            List<Users> l = q1.getResultList();

            System.out.println("DB ye baglandim");

            String userName = uId.getText();
            String pass = uPass.getText();
            int userType;
            int userCampID;
            for (int i = 0; i < l.size(); i++) {
                if (userName.equals((String) l.get(i).getUsername()) && pass.equals((String) l.get(i).getPass())) {
                    userType = l.get(i).getUsertType();
                    userCampID = Integer.parseInt(l.get(i).getCampId());
                    // userType = 1 is admin, 2 is personel.
                    label.setText("Giriş Başarılı");
                    //If he is admin
                    if (userType == 1) {
                        FXMLLoader Loader = new FXMLLoader();
                        Loader.setLocation(getClass().getResource("Camps.fxml"));
                        try {
                            Loader.load();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                        CampsController cc = Loader.getController();

                        EntityManager em2 = dbConnection.newEntityManager();
                        TypedQuery<CampSite> q2 = em2.createQuery("SELECT c FROM CampSite c", CampSite.class);
                        List<CampSite> l2 = q2.getResultList();

                        ObservableList<CampsTable> cList = FXCollections.observableArrayList();

                        for (int k = 0; k < l2.size(); k++) {
                            String namee = l2.get(k).getName();
                            String locationn = l2.get(k).getLocation();
                            String campTypee = cc.findCampType(l2.get(k));

                            cList.add(new CampsTable(namee, locationn, campTypee));
                        }
                        em2.close();
                        cc.setCampSites(cList);
                        cc.setColumns();
                        Parent p = Loader.getRoot();
                        Stage s = new Stage();
                        s.setTitle("All CampSites");
                        s.setScene(new Scene(p));
                        s.show();
                        Login.stage.hide();
                        //sceneTransition("Camps.fxml");
                    } else if (userType == 2) { //or he is a personel
//                        FXMLLoader Loader = new FXMLLoader();
//                        Loader.setLocation(getClass().getResource("Tents.fxml"));
//                        try {
//                            Loader.load();
//                        } catch (IOException ex) {
//                            System.out.println(ex);
//                        }
//                        TentsController tc = Loader.getController();
//                        EntityManager em2 = dbConnection.newEntityManager();
//                        TypedQuery<Tent> q3 = em2.createQuery("SELECT t FROM Tent t", Tent.class);
//                        List<Tent> l3 = q3.getResultList();
//
//                        ObservableList<TentTable> tList = FXCollections.observableArrayList();
//
//                        for (int j = 0; j < l3.size(); j++) {
//                            String tentType = tc.findTentType(l3.get(j));
//                            String description = l3.get(j).getDescription();
//                            String tentId = l3.get(j).getId() + "";
//                            tList.add(new TentTable(tentId, tentType, description));
//                        }
//                        em2.close();
//
//                        tc.setTents(tList);
//                        tc.setColumns();
//                        Parent p = Loader.getRoot();
//                        Stage s = new Stage();
//                        String campNamesi = tc.findCampName(userCampID);
//                        tc.setCampID(userCampID);
//                        s.setTitle(campNamesi + " Campsite");
//                        tc.setTentTitle("All Tents in " + campNamesi + " Campsite");
//                        s.setScene(new Scene(p));
//                        s.show();
//                        Login.stage.close();
                        sceneTransition("Tents.fxml");
                    }
                    break;
                }
            }
            em.close();
            label.setText("Your username or password is wrong. Please check it and try again..");
            //}
        } catch (Exception ex) {
            System.out.println("DB YE BAGLANAMADIM" + ex);
        }

    }

    public DatabaseConnection getDbConnection() {
        return dbConnection;
    }

    public void setD(DatabaseConnection d) {
        this.dbConnection = d;
    }

    public void sceneTransition(String sceneFxml) {

        Stage stage = Login.stage;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(sceneFxml));
            Parent roott = loader.load();
            Scene scene = new Scene(roott);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
