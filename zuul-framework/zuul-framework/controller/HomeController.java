package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import player.Country;

public class HomeController extends GenericController{
    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView bedImage;

    // popup window

    @FXML
    private AnchorPane popupPane;

    @FXML
    private Text headerText;

    @FXML
    private Text dialogText;

    @FXML
    private Button okButton;


    public HomeController() throws  Exception{
        super();
    }

    // Initialize is called in RessourceController::loadRooms when creating an instance of HomeController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {

        Country country = Run.getRInstance().getPlayer().getCountry();
        Image backgroundImage = new Image("/png/dkHome.png");
        Image doorImage = new Image("/png/dkDoor.png");
        Image bedImage = new Image("/png/dkBed.png");

        switch (country){
            case DANHEIM -> {
                backgroundImage = new Image("/png/dkHome.png");
                doorImage = new Image("/png/dkDoor.png");
                bedImage = new Image("/png/dkBed.png");
            }
            case VAKANNDA -> {
                backgroundImage = new Image("/png/ugHome.png");
                doorImage = new Image("/png/ugDoor.png");
                bedImage = new Image("/png/ugBed.png");
            }
            case WASHINGGEORGE -> {
                backgroundImage = new Image("/png/usaHome.png");
                doorImage = new Image("/png/usaDoor.png");
                bedImage = new Image("/png/usaBed.png");
            }
        }

        this.backgroundImage.setImage(backgroundImage);
        this.doorImage.setImage(doorImage);
        this.bedImage.setImage(bedImage);

    }

    @FXML
    void displayPopup(MouseEvent event) {

        headerText.setText("Sleep");
        dialogText.setText("You sleep");

        popupPane.setVisible(true);
    }

    @FXML
    void hidePopup(MouseEvent event) {
        popupPane.setVisible(false);
    }
}
