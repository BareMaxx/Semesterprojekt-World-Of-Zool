package gameEngine;

import commands.Command;
import commands.Parser;
import controller.StartmenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;

public class Run extends Application {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private Parser parser;
    private Player player;
    private Child c;
    private Adult a;
    private Old o;

    public Stage primaryStage;

    public Run() {
        parser = new Parser();
        player = new Player();
        c = new Child(player);
        a = new Adult(player);
        o = new Old(player);
    }

    public void launchMenu(){
        launch();
    }

    public void processCommand(String input) {
        Command command = parser.getCommand(input);

        switch (player.getStage()) {
            case "child" -> c.processCommand(command);
            case "adult" -> a.processCommand(command);
            case "old" -> o.processCommand(command);
        }
    }

    public void changeScene(String fxmlFileName) throws Exception {
        processCommand("go " + fxmlFileName);

        String fxmlFile = "/fxml/" + fxmlFileName + ".fxml";

        FXMLLoader root = new FXMLLoader(getClass().getResource(fxmlFile));

        primaryStage.setScene(new Scene(root.load(), WIDTH , HEIGHT));
    }

    public void initGame(String country){
        new InitGame(player, country);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        StartmenuController controller = new StartmenuController(this, primaryStage);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/startmenu.fxml"));
        root.setController(controller);

        primaryStage.setTitle("Outside");
        primaryStage.setScene(new Scene(root.load(), WIDTH , HEIGHT));
        primaryStage.show();
    }
}