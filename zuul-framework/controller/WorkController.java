package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Country;

public class WorkController extends GenericController {

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView workstationImage;

    // Initialize is called in RessourceController::loadRooms when creating an instance of OutsideController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {

        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkWorkBG.png"));
                doorImage.setImage(new Image("/png/dkWorkDoor.png"));
                workstationImage.setImage(new Image("/png/workPlacePC.png"));
            }
            case VAKANNDA -> {
                /*
                backgroundImage.setImage(new Image("/png/ugWorkBF.jpg"));
                doorImage.setImage(new Image("/png/ugSign.png"));
                workstationImage.setImage(new Image("/png/ugSign.png"));
                */
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/usaWorkBG.png"));
                doorImage.setImage(new Image("/png/usaWorkDoor.png"));
                workstationImage.setImage(new Image("/png/workPlacePC.png"));
            }
        }
    }

    @FXML
    void work(MouseEvent event) {
        Run.getRInstance().processCommand("work");
    }

}
