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
                backgroundImage.setImage(new Image("/png/dkHomeBG.png"));
                doorImage.setImage(new Image("/png/dkusaHomeDoor.png"));
                bedImage.setImage(new Image("/png/dkHomeBed.png"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugHomeBG.png"));
                doorImage.setImage(new Image("/png/ugHomeDoor.png"));
                bedImage.setImage(new Image("/png/ugHomeBed.png"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/usaHomeBG.png"));
                doorImage.setImage(new Image("/png/dkusaHomeDoor.png"));
                bedImage.setImage(new Image("/png/usaHomeBed.png"));
            }
        }
    }
}
