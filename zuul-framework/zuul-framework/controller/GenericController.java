package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import player.Player;

public class GenericController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    public void initialize(){

    }

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
}
