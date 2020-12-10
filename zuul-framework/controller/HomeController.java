package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Country;

public class HomeController extends GenericController{

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView bedImage;

    // Call the sleep command
    @FXML
    void sleep(MouseEvent event) {
        Run.getRInstance().processCommand("sleep");
    }

    // Initialize is called in RessourceController::loadRooms when creating an instance of HomeController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {
        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkHome.png"));
                doorImage.setImage(new Image("/png/dkDoor.png"));
                bedImage.setImage(new Image("/png/dkBed.png"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugHome.png"));
                doorImage.setImage(new Image("/png/ugDoor.png"));
                bedImage.setImage(new Image("/png/ugBed.png"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/usaHome.png"));
                doorImage.setImage(new Image("/png/usaDoor.png"));
                bedImage.setImage(new Image("/png/usaBed.png"));
            }
        }
    }
}
