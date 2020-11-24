package main;

import javafx.application.Application;
import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        /*
        Game game = new Game();
        game.play();
        */
        //Run r = new Run();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/outside.fxml"));
        primaryStage.setTitle("Outside");
        primaryStage.setScene(new Scene(root, 1280 , 720));
        primaryStage.show();
    }
}