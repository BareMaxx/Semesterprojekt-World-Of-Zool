package controller;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.stage.Window;
import player.Player;

import gameEngine.Run;

public class GenericController {

    protected static Run r;
    protected static Stage primaryStage;

    public GenericController(Run r, Stage primaryStage){
        this.r = r;
        this.primaryStage = primaryStage;
    }

    public GenericController(){

    }

    @FXML
    private ImageView backgroundImage;

    @FXML
    void goRoom(MouseEvent event) {
        System.out.print("Test\n");
    }

    @FXML
    void darkenText(MouseEvent event) {

        Text text = (Text)event.getTarget();
        text.setStroke(Color.WHITE);
    }

    @FXML
    void highlightText(MouseEvent event) {

        Text text = (Text)event.getTarget();
        text.setStroke(Color.YELLOW);

    }

    @FXML
    void goOutside(MouseEvent event) throws Exception{
        changeScene("outside");
    }

    @FXML
    void changeScene(String fxmlFileName) throws Exception{

        String fxmlFile = ("/fxml/" + fxmlFileName + ".fxml");

        FXMLLoader root = new FXMLLoader(getClass().getResource(fxmlFile));

        primaryStage.setScene(new Scene(root.load(), 1280 , 720));
    }
}
