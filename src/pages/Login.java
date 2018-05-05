package pages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application{
    
   public static Stage stage;
   
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
   @Override 
   public void start(Stage s) {
      s.setTitle("Sample FXML Program");
      try{
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Login.fxml")) ;
      Parent roott = loader.load();
      Scene scene = new Scene(roott);
      s.setScene(scene);
      s.show();
      setStage(s);
      }catch(Exception ex){
        ex.printStackTrace();
      }
   }
   public static void main(String[] args) {
      Application.launch(args);
   }
}