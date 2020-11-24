package gameEngine;

import commands.Parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;

public class Run extends Application {

    private Parser parser;
    Player p1;
    Child c;
    Adult a;
    Old o;

    public Run() {
        parser = new Parser();
        p1 = new Player();
        c = new Child(p1, parser);
        a = new Adult(p1, parser);
        o = new Old(p1, parser);
    }

    public void GameLoop(){

        launch();

        /*
        new InitGame(p1, parser);

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/outside.fxml"));
        primaryStage.setTitle("Outside");
        primaryStage.setScene(new Scene(root, 1280 , 720));
        primaryStage.show();
    }
}