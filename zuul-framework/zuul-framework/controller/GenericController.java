package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import gameEngine.Run;

public class GenericController {
    @FXML
    private ImageView backgroundImage;

    // popup window

    @FXML
    private AnchorPane popupPane;

    @FXML
    private Text headerText;

    @FXML
    private Text dialogText;

    @FXML
    private Button okButton;

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

    @FXML
    void displayPopup(String header, String dialog) {

        headerText.setText(header);
        dialogText.setText(dialog);

        popupPane.setVisible(true);
    }

    @FXML
    void hidePopup(MouseEvent event) {
        popupPane.setVisible(false);
    }
}
