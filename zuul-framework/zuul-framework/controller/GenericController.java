package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import gameEngine.Run;

public class GenericController {
    @FXML
    private ImageView backgroundImage;

    @FXML
    public void displayStartmenu() throws Exception{
        changeScene("startmenu");
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
    void changeScene(String room) throws Exception{

        // application layer
        Run.getRInstance().processCommand("go " + room);

        switch (room){
            case "home" ->{
                Run.getPrimaryStage().setScene(ResourceController.getHomeScene());
                return;
            }
            case "outside" ->{
                Run.getPrimaryStage().setScene(ResourceController.getOutsideScene());
                return;
            }
            case "startmenu" ->{
                Run.getPrimaryStage().setScene(ResourceController.getStartmenuScene());
                return;
            }
        }
    }
}
