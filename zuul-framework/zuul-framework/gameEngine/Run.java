package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
import controller.GenericController;
import controller.RessourceController;
import controller.StartmenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;

import java.util.Scanner;

public class Run extends Application {

    private Parser parser;
    private Player p1;
    private Child c;
    private Adult a;
    private Old o;

    private static Run rInstance;
    private static Stage primaryStage;

    public Run() {
        rInstance = this;
        parser = new Parser();
        p1 = new Player();
        c = new Child(p1, parser);
        a = new Adult(p1, parser);
        o = new Old(p1, parser);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Run getRInstance() {
        return rInstance;
    }

    public void launchMenu(){
        launch();
    }

    public void processCommand(String input) {
        Command command = parser.getCommand(input);

        switch (p1.getStage()) {
            case "child" -> c.processCommand(command);
            case "adult" -> a.processCommand(command);
            case "old" -> o.processCommand(command);
        }
    }

    public void initGame(String country){

        new InitGame(p1, country);
    }

    // This function is called when an instance of the Run class is created.
    // This is due to the fact that Run extends the JavaFX class Application.
    // See JavaFX documentation for further explanation...
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        RessourceController ressourceController = new RessourceController();
        ressourceController.loadRessources();
    }
}