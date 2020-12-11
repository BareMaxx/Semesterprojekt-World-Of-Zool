package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Country;

public class HospitalController extends GenericController {

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView bedImage;

    // Initialize is called in RessourceController::loadRooms when creating an instance of WorkController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {
        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkusaHospitalBG.png"));
                doorImage.setImage(new Image("/png/dkusaHospitalDoor.png"));
                bedImage.setImage(new Image("/png/dkusaHospitalBed.png"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugHospitalBG.png"));
                doorImage.setImage(new Image("/png/ugHospitalDoor.png"));
                bedImage.setImage(new Image("/png/ugHospitalBed.png"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/dkusaHospitalBG.png"));
                doorImage.setImage(new Image("/png/dkusaHospitalDoor.png"));
                bedImage.setImage(new Image("/png/dkusaHospitalBed.png"));
            }
        }
    }

    // Call the heal command
    @FXML
    void heal(MouseEvent event) {
        Run.getRInstance().processCommand("heal");
    }
}
