package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class OutsideController extends GenericController {
    @FXML
    void goHome(MouseEvent event) throws Exception{
        changeScene("home");
    }

    @FXML
    void goSchool(MouseEvent event) throws Exception {
        changeScene("school");
    }

    @FXML
    void goWork(MouseEvent event) throws Exception {
        changeScene("work");
    }

    @FXML
    void goShop(MouseEvent event) throws Exception {
        changeScene("shop");
    }

    @FXML
    void goHospital(MouseEvent event) throws Exception {
        changeScene("hospital");
    }
}
