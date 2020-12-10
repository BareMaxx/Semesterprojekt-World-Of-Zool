package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import gameEngine.Run;

public class StartmenuController extends GenericController {
    // Start the game depending on the player's choice of country
    @FXML
    void startGame(MouseEvent event) throws Exception{
        Text text = (Text)event.getTarget();
        String country = text.getText();

        // Initialize the map, load assets and set the starting scene
        Run.getRInstance().initGame(country);
        ResourceController.loadRooms();
        changeScene("home");

        // Update money textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateMoney();

        // Update gender textfield in overlay
        String gender = Run.getRInstance().getPlayer().getGender().toString();
        ((OverlayController) ResourceController.getOverlayData().controller).setGenderText(gender);

        // Update familyeconomy textfield in overlay
        String familyEconomy = Run.getRInstance().getPlayer().getFamilyEconomy().toString();
        ((OverlayController) ResourceController.getOverlayData().controller).setFamilyEconomyText(familyEconomy);
    }

    // Override highlight and darkentext
    @FXML
    @Override
    void darkenText(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setFill(Color.BLACK);
    }

    @FXML
    @Override
    void highlightText(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setFill(Color.YELLOW);
    }

    @FXML
    void playGame(MouseEvent event) throws Exception {
        ResourceController.loadMenu();
        Run.getPrimaryStage().setScene(ResourceController.getStartmenuData().scene);
    }
}
