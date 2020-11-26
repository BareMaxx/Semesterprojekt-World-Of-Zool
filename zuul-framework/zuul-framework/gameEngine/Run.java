package gameEngine;

import commands.Command;
import commands.Parser;
import controller.ResourceController;
import javafx.application.Application;
import javafx.stage.Stage;
import player.Player;

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
        c = new Child(getP1(), parser);
        a = new Adult(getP1(), parser);
        o = new Old(getP1(), parser);
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

        switch (getP1().getStage()) {
            case "child" -> c.processCommand(command);
            case "adult" -> a.processCommand(command);
            case "old" -> o.processCommand(command);
        }
    }

    public void initGame(String country){
        new InitGame(getP1(), country);
    }

    // This function is called when an instance of the Run class is created.
    // This is due to the fact that Run extends the JavaFX class Application.
    // See JavaFX documentation for further explanation...
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        ResourceController resourceController = new ResourceController();
        resourceController.loadMenu();
    }

    public Player getP1() {
        return p1;
    }
}