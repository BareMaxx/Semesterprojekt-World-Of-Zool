package controller;

import gameEngine.Run;
import item.Book;
import item.Item;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Country;

import java.util.ArrayList;

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
        if (Run.getRInstance().getPlayer().getStage().equals("child")) {
            ArrayList<Item> playerInevntory = Run.getRInstance().getPlayer().getInventory();

            for (Item item: playerInevntory){
                if (item instanceof Book){
                    Run.getRInstance().processCommand("use " + item.getName());

                    Run.getRInstance().getPlayer().getInventory().remove(item);
                    ((OverlayController) ResourceController.getOverlayData().controller).updateInventory();

                    // This function returns when a book is read
                    // (only one book is read when the book image is clicked)
                    return;
                }
            }
            System.out.println("\nYou need to buy a book in the shop");
        } else {
            System.out.println("\nYou can only read while being a child");
        }
        Run.getRInstance().updateEventlog();
    }
}
