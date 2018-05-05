package pages;

import DatabaseClasses.*;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.persistence.Query;

public class RefugeeController{
   @FXML
   private Button backLoginPage;
   
  
   @FXML
   private void mouseClicked(MouseEvent event){
      LoginController lg = new LoginController();
      lg.sceneTransition("Login.fxml");
   }

}