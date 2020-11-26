package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import gameEngine.Run;

public class StartmenuController extends GenericController{
    @FXML
    void startGame(MouseEvent event) throws Exception{

        Text text = (Text)event.getTarget();
        String country = text.getText();

        Run.getRInstance().initGame(country);
        ResourceController.getResourceControllerInstance().loadRooms();
        changeScene("home");
    }

    @FXML
    @Override
    void darkenText(MouseEvent event) {

        Text text = (Text)event.getTarget();
        text.setFill(Color.BLACK);
    }

    @FXML
    @Override
    void highlightText(MouseEvent event) {

        Text text = (Text)event.getTarget();
        text.setFill(Color.YELLOW);

    }
}
