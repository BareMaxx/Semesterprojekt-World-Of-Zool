package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
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

    public Stage primaryStage;

    public Run() {
        parser = new Parser();
        p1 = new Player();
        c = new Child(p1, parser);
        a = new Adult(p1, parser);
        o = new Old(p1, parser);
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

        /*
        while (p1.getAlive()) {
            switch (p1.getStage()) {
                case "child" -> c.play();
                case "adult" -> a.play();
                case "old" -> o.play();
            }
        }
        System.out.println("Thank you for playing.  Good bye.");

         */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        StartmenuController controller = new StartmenuController(this, primaryStage);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/startmenu.fxml"));
        root.setController(controller);

        primaryStage.setTitle("Outside");
        primaryStage.setScene(new Scene(root.load(), 1280 , 720));
        primaryStage.show();
    }
}