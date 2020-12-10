package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HospitalController extends GenericController {
    // Call the heal command
    @FXML
    void heal(MouseEvent event) {
        Run.getRInstance().processCommand("heal");
    }
}
