package src.loginpage;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController{
   @FXML
   private Button button;
   @FXML
   private TextField text;
   @FXML
   private Label label;
   
   @FXML
   private void mouseClicked(MouseEvent event){
      label.setText(text.getText());
   }

}