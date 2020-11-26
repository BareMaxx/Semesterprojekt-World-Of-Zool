
package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import gameEngine.Run;
import javafx.stage.Stage;

public class StartmenuController extends GenericController{

    public StartmenuController(Run r, Stage primaryStage){
        super(r, primaryStage);
    }

    @FXML
    void startGame(MouseEvent event) throws Exception{

        Text text = (Text)event.getTarget();
        String country = text.getText();

        r.initGame(country);
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
