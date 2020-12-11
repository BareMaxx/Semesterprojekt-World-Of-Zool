package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Country;

public class SchoolController extends GenericController {

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView bookImage;


    // Initialize is called in RessourceController::loadRooms when creating an instance of SchoolController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {
        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkusaSchoolBG.png"));
                doorImage.setImage(new Image("/png/dkusaSchoolDoor.png"));
                bookImage.setImage(new Image("/png/schoolBook.png"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugSchoolBG.png"));
                doorImage.setImage(new Image("/png/ugSchoolDoor.png"));
                bookImage.setImage(new Image("/png/schoolBook.png"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/dkusaSchoolBG.png"));
                doorImage.setImage(new Image("/png/dkusaSchoolDoor.png"));
                bookImage.setImage(new Image("/png/schoolBook.png"));
            }
        }
    }

    @FXML
    void readBook(MouseEvent event) {

    }
}
