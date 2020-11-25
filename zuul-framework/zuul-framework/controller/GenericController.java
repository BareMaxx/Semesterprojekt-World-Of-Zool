package controller;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.event.Event;
import javafx.stage.Window;
import player.Player;

import gameEngine.Run;

public class GenericController {

    Run r;

    public GenericController(Run r){
        this.r = r;
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
    void changeScene(EventTarget event){

        Text text = (Text)event;
        Window window = text.getScene().getWindow();
    }
}
