package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import gameEngine.Run;

public class OutsideController extends GenericController {
    public OutsideController(){
        super();
    }

    @FXML
    void goHome(MouseEvent event) throws Exception{
        changeScene("home");
    }
}
