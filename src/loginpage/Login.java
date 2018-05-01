package src.loginpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application{
   
   @Override 
   public void start(Stage s) {
      s.setTitle("Sample FXML Program");
      s.setWidth(800);
      s.setHeight(600);
      try{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
      AnchorPane root = loader.load();
      Scene scene = new Scene(root);
      s.setScene(scene);
      s.show();
      }catch(Exception ex){
        ex.printStackTrace();
      }
   }
   public static void main(String[] args) {
      Application.launch(args);
   }
}