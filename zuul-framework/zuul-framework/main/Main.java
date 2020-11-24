package main;

import javafx.application.Application;
import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        /*
        Game game = new Game();
        game.play();
        */
        Run r = new Run();
        r.GameLoop();


    }
}