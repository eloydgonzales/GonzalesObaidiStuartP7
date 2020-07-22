/**************************
 * Michael Stuart         *
 * mstuart3@cnm.edu       *
 * GonzalesStuartObaidiP7 *
 * Main.java              *
 * ************************/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GonzalesObaidiStuartP7 - 3 Card Brag"); //MS added program title
        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.setResizable(false); //MS don't allow user to resize window
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
