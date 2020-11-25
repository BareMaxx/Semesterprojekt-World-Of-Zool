
package controller;

import gameEngine.InitGame;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import gameEngine.Run;

public class StartmenuController extends GenericController{

    public StartmenuController(Run r){
        super(r);
    }

    @FXML
    void startGame(MouseEvent event) {

        Text text = (Text)event.getTarget();
        String country = text.getText();

        r.initGame(country);
        changeScene(event.getTarget());
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
