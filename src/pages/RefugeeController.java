package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML
    private Button remove;
    @FXML
    private Button transport;
    @FXML
    private Label searchResultLbl;
    @FXML
    private TextField socialText;

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
        try {
            Loader.load();
        } catch (IOException ex) {
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
        refugeeStage.hide();
        s.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                //TentsController tc = new TentsController();
                //tc.detailClicked(event);
                refugeeStage.show();
            }
        });

    }

    @FXML
    private void editRef(MouseEvent event) {

        RefugeeTable rt = refugeesTable.getSelectionModel().getSelectedItem();
        System.out.println(rt.getName() + "--" + rt.getSurName());
        String socialId = rt.getSocialId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.socialId "
                + "='" + socialId + "'", Refugee.class);
        Refugee l = q1.getSingleResult();
        em.close();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("EditRefugee.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        EditRefugeeController erc = Loader.getController();
        erc.setR(l);
        erc.setRc(this);
        erc.init();
        Parent p = Loader.getRoot();
        Stage s = new Stage();
        s.setScene(new Scene(p));
        erc.setS(s);
        s.show();
        s.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                TentsController tc = new TentsController();
                tc.detailClicked(event);
            }
        });
        refugeeStage.close();
    }

    public ObservableList<RefugeeTable> listTable() {
        int campId = getCampId();

        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.campId ='" + TentsController.campID + "'AND "
                + "r.isStay='" + 1 + "'AND r.tentId='" + TentsController.tentId + "'", Refugee.class);
        List<Refugee> l = q1.getResultList();
        em.close();

        ObservableList<RefugeeTable> rList = FXCollections.observableArrayList();
        refugees.clear();
        for (int i = 0; i < l.size(); i++) {
            rList.add(new RefugeeTable(l.get(i).getName(),
                    l.get(i).getSurname(),
                    l.get(i).getNationality(),
                    findCampName(l.get(i)),
                    findTentName(l.get(i)),
                    l.get(i).getSocialId(),
                    findGender(l.get(i)),
                    findIsAlive(l.get(i))));
        }

        return rList;
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

        name.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("surName"));
        nationality.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("nation"));
        campName.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("campName"));
        tentName.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("tentName"));
        socialId.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("socialId"));
        gender.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("gender"));
        alive.setCellValueFactory(new PropertyValueFactory<RefugeeTable, String>("alive"));

        refugeesTable.getColumns().clear();
        refugeesTable.getColumns().addAll(name, lastName, nationality, campName, tentName, socialId, gender, alive);

        refugeesTable.setItems(listTable());
    }

    public String findCampName(Refugee r) {
        int campId = r.getCampId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<CampSite> q1 = em.createQuery("SELECT c FROM CampSite c WHERE c.id = '" + campId + "'", CampSite.class);
        CampSite c = q1.getSingleResult();
        em.close();
        return c.getLocation() + "/" + c.getName();
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
        if (r.getGender() == 0) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String findIsAlive(Refugee r) {
        if (r.getIsAlive() == 0) {
            return "Dead";
        } else {
            return "Alive";
        }
    }

    @FXML
    private void remove(MouseEvent event) {
        RefugeeTable rt = refugeesTable.getSelectionModel().getSelectedItem();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.socialId ='" + rt.getSocialId() + "'", Refugee.class);
        Refugee r = q1.getSingleResult();
        int deletedCount = em.createQuery("DELETE FROM Refugee r WHERE r.id ='" + r.getId() + "'").executeUpdate();
        em.getTransaction().commit();
        em.close();
        setColumns();
    }

    @FXML
    private void transport(MouseEvent event) {
        RefugeeTable rt = refugeesTable.getSelectionModel().getSelectedItem();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.socialId ='" + rt.getSocialId() + "'", Refugee.class);
        Refugee r = q1.getSingleResult();
        Refugee rr = em.find(Refugee.class, r.getId());
        rr.setIsStay(0);
        em.persist(rr);
        em.getTransaction().commit();
        em.close();
        setColumns();
    }

    @FXML
    public void onEnter(ActionEvent ae) {
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.socialId ='" + socialText.getText() + "'", Refugee.class);
        List<Refugee> r = q1.getResultList();
        if (r.isEmpty()) {
            searchResultLbl.setText("Fot Found");
        } else {
            searchResultLbl.setText("Found");
            em.close();
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("EditRefugee.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            EditRefugeeController erc = Loader.getController();
            erc.setR(r.get(0));
            erc.setRc(this);
            erc.init();
            Parent p = Loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            erc.setS(s);
            s.show();
        }
    }

}
