package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class WorkController extends GenericController {
    @FXML
    void work(MouseEvent event) {
        Run.getRInstance().processCommand("work");
    }
}
