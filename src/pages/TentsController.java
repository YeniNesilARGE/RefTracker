package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tableObjects.CampsTable;
import tableObjects.RefugeeTable;
import tableObjects.TentTable;

public class TentsController {

    @FXML
    private TableView<TentTable> tentsTable = new TableView<>();
    @FXML
    private Button addTentButton;
    @FXML
    private Button tentDetailButton;
    @FXML
    private Label tentTitle = new Label();

    static int campID;
    static int tentId;
    static int tentId2;

    public int getTentId2() {
        return tentId2;
    }

    public void setTentId2(int tentId2) {
        this.tentId2 = tentId2;
    }

    public int getCampID() {
        return campID;
    }

    public void setCampID(int campID) {
        this.campID = campID;
    }

    public void setTentTitle(String tentTitle) {
        this.tentTitle.setText(tentTitle);
    }

    @FXML
    private void addClicked(MouseEvent event) {
        //add camp
    }

    @FXML
    public void detailClicked(MouseEvent event) {
        try {
            campID = getCampID();
            if (findTentId() != -1) {
                tentId = findTentId();
            }
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Refugee.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            RefugeeController rc = Loader.getController();
            EntityManager em = LoginController.dbConnection.newEntityManager();
            TypedQuery<Refugee> q1 = em.createQuery("SELECT r FROM Refugee r WHERE r.campId ='" + getCampID() + "'AND "
                    + "r.isStay='" + 1 + "'AND r.tentId='" + findTentId() + "'", Refugee.class);

            List<Refugee> l = q1.getResultList();

            ObservableList<RefugeeTable> rList = FXCollections.observableArrayList();

            for (int i = 0; i < l.size(); i++) {

                rList.add(new RefugeeTable(l.get(i).getName(),
                        l.get(i).getSurname(),
                        l.get(i).getNationality(),
                        rc.findCampName(l.get(i)),
                        rc.findTentName(l.get(i)),
                        l.get(i).getSocialId(),
                        rc.findGender(l.get(i)),
                        rc.findIsAlive(l.get(i))));
            }

            em.close();
            rc.setRefugees(rList);
            rc.setColumns();
            rc.setCampId(getCampID());
            if (findTentId() != -1) {
                rc.setTentId(findTentId());
            } else {
                rc.setTentId(getTentId2());
            }

            Parent p = Loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            rc.setRefugeeStage(s);
            s.showAndWait();

        } catch (Exception ex) {
            System.out.println("DB YE BAGLANAMADIM" + ex);
        }
    }

    @FXML
    private void findClicked(MouseEvent event) {

    }
    private ObservableList<TentTable> tents = FXCollections.observableArrayList();

    public void setTents(ObservableList<TentTable> tents) {
        this.tents = tents;
    }

    public ObservableList<TentTable> getTents() {
        return tents;
    }

    public void setColumns() {
        TableColumn id = new TableColumn("Tent ID");
        TableColumn tentType = new TableColumn("Tent Type");
        TableColumn description = new TableColumn("Description");

        id.setCellValueFactory(new PropertyValueFactory<CampsTable, String>("id"));
        tentType.setCellValueFactory(new PropertyValueFactory<CampsTable, String>("tentType"));
        description.setCellValueFactory(new PropertyValueFactory<CampsTable, String>("description"));

        tentsTable.getColumns().clear();
        tentsTable.getColumns().addAll(id, tentType, description);
        tentsTable.setItems(tents);
    }

    public int findTentId() {
        TentTable tent = tentsTable.getSelectionModel().getSelectedItem();
        if (tent != null) {
            System.out.println(tent.getId());
            return Integer.parseInt(tent.getId());
        } else {
            return -1;
        }
    }

    public String findTentType(Tent tent) {
        int tentTypeID = tent.getTypeId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<TentType> q1 = em.createQuery("SELECT t FROM TentType t WHERE t.id ='" + tentTypeID + "'", TentType.class);
        TentType tt = q1.getSingleResult();
        return tt.getType();
    }

    public String findCampName(Tent tent) {
        int campID = tent.getCampId();
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<CampSite> q1 = em.createQuery("SELECT c FROM CampSite c WHERE c.id ='" + campID + "'", CampSite.class);
        CampSite cs = q1.getSingleResult();
        return cs.getName();
    }

    public String findCampName(int id) {
        EntityManager em = LoginController.dbConnection.newEntityManager();
        TypedQuery<CampSite> ql = em.createQuery("SELECT c FROM CampSite c WHERE c.id ='" + id + "'", CampSite.class);
        CampSite cs = ql.getSingleResult();
        return cs.getName();
    }
}
