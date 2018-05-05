package pages;

import DatabaseClasses.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class LoginController {

    @FXML
    private Button button;
    @FXML
    private TextField uId;
    @FXML
    private TextField uPass;
    @FXML
    private Label label;

    static DatabaseConnection dbConnection = new DatabaseConnection();

    @FXML
    private void mouseClicked(MouseEvent event) {
        try {
            
            dbConnection.connDb();
            TypedQuery<Users> q1 = dbConnection.getEm().createQuery("SELECT u FROM Users u",Users.class);
            List<Users> l =  q1.getResultList();
            
            
            System.out.println("DB ye baglandim");
            String userName = uId.getText();
            String pass = uPass.getText();
            for (int i = 0; i < l.size(); i++) {
            
            if (userName.equals((String)l.get(i).getUsername()) && pass.equals((String)l.get(i).getPass())){
                    label.setText("Giriş Başarılı");
                    sceneTransition("Tents.fxml");
                    break;
                }
            }

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
