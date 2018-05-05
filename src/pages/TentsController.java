package pages;

import DatabaseClasses.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.TypedQuery;
import tableObjects.RefugeeTable;

public class TentsController {

    @FXML
    private TableView tentsTable;
    @FXML
    private Button addTentButton;
    @FXML
    private Button tentDetailButton;
    @FXML
    private Label tentTitle;

    @FXML
    private void addClicked(MouseEvent event) {
        //add camp
    }

    @FXML
    private void detailClicked(MouseEvent event) {
        //camp detail
    }

    @FXML
    private void findClicked(MouseEvent event) {
        //find camp

        //tıklanan camp id'si örneğin 1 olsun.
        //detay'a tıklandıktan sonra sığınmacıların tablosu gelecek bu yüzden refugeecontroller sınıfındaki değişken değerlerinin parametreleri
        //buradan yollanmalı
        int cId = 1;

        //hangi kampa tıklanıldığı bedirhan tarafından kontrol edilecek
        try {

            
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Refugee.fxml"));
            try{
                Loader.load();
            }catch(IOException ex){
                System.out.println(ex);
            }
            RefugeeController rc = Loader.getController();
            
            TypedQuery<Refugee> q1 = LoginController.dbConnection.getEm().createQuery("SELECT r FROM Refugee r WHERE r.campId ='" + cId + "'" , Refugee.class);
            List<Refugee> l = q1.getResultList();
            
            ArrayList<RefugeeTable> rList = new ArrayList<>();
            
            for (int i = 0; i < l.size(); i++) {
                rList.add(new RefugeeTable(l.get(i).getName(),l.get(i).getSurname(),l.get(i).getNationality(),
                        rc.findCampName(l.get(i)) , rc.findTentName(l.get(i)), rc.findGender(l.get(i)), rc.findIsAlive(l.get(i))));
            }
            rc.setRefugees(rList);
            rc.setColumns();
            Parent p = Loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.showAndWait();
            
        } catch (Exception ex) {
            System.out.println("DB YE BAGLANAMADIM" + ex);
        }
    }

}
