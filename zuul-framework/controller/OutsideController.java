package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import player.Country;

public class OutsideController extends GenericController {

    // Initialize is called in RessourceController::loadRooms when creating an instance of HomeController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {

        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkOutside.jpg"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugOutside.jpg"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/usaOutside.jpg"));
            }
        }
    }

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

    @FXML
    @Override
    void goOutside(MouseEvent event) throws Exception{}
}
