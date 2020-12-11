package controller;

import gameEngine.Run;
import gameplay.Room;
import item.PurchasableItem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import player.Country;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopController extends GenericController {

    @FXML
    private ImageView Algorithms;

    @FXML
    private ImageView Sql;

    @FXML
    private ImageView Math;

    @FXML
    private ImageView Helmet;

    @FXML
    private ImageView Mask;

    @FXML
    private TextField AlgorithmsTextfield;

    @FXML
    private TextField MathTextfield;

    @FXML
    private TextField SqlTextfield;

    @FXML
    private TextField maskTextfield;

    @FXML
    private TextField helmetTextfield;

    // Initialize is called in RessourceController::loadRooms when creating an instance of ShopController.
    // It is called automatically by JavaFX.
    @FXML
    public void initialize() {

        Country country = Run.getRInstance().getPlayer().getCountry();

        switch (country){
            case DANHEIM -> {
                backgroundImage.setImage(new Image("/png/dkusaShopBG.png"));
            }
            case VAKANNDA -> {
                backgroundImage.setImage(new Image("/png/ugShopBG.png"));
            }
            case WASHINGGEORGE -> {
                backgroundImage.setImage(new Image("/png/dkusaShopBG.png"));
            }
        }

        // Set the correct text on the item textfields
        ArrayList<PurchasableItem> shopItemList = Run.getShopRoom().getItems();

        for (PurchasableItem item: shopItemList){

            switch (item.getName()){
                case "Algorithms" ->{AlgorithmsTextfield.setText("Algorithms - " + item.getPrice());}
                case "Math" ->{MathTextfield.setText("Math - " + item.getPrice());}
                case "Sql" ->{SqlTextfield.setText("Sql - " + item.getPrice());}
                case "Helmet" ->{helmetTextfield.setText("Helmet - " + item.getPrice());}
                case "Mask" ->{maskTextfield.setText("Mask - " + item.getPrice());}
            }
        }
    }

    @FXML
    void buy(MouseEvent event) {

        ImageView target = (ImageView)event.getTarget();

        Run.getRInstance().processCommand("buy " + target.getId());

        if (Run.getShopRoom().getItem(target.getId()) == null){

            target.setVisible(false);

            switch (target.getId()){
                case "Algorithms" ->{AlgorithmsTextfield.setVisible(false);}
                case "Math" ->{MathTextfield.setVisible(false);}
                case "Sql" ->{SqlTextfield.setVisible(false);}
                case "Helmet" ->{helmetTextfield.setVisible(false);}
                case "Mask" ->{maskTextfield.setVisible(false);}
            }
        }
    }

    @FXML
    void hidePrice(MouseEvent event) {

        ImageView target = (ImageView)event.getTarget();

        switch (target.getId()){
            case "Algorithms" ->{AlgorithmsTextfield.setVisible(false);}
            case "Math" ->{MathTextfield.setVisible(false);}
            case "Sql" ->{SqlTextfield.setVisible(false);}
            case "Helmet" ->{helmetTextfield.setVisible(false);}
            case "Mask" ->{maskTextfield.setVisible(false);}
        }
    }

    @FXML
    void showPrice(MouseEvent event) {

        ImageView target = (ImageView)event.getTarget();

        switch (target.getId()){
            case "Algorithms" -> {AlgorithmsTextfield.setVisible(true);}
            case "Math" ->{MathTextfield.setVisible(true);}
            case "Sql" ->{SqlTextfield.setVisible(true);}
            case "Helmet" ->{helmetTextfield.setVisible(true);}
            case "Mask" ->{maskTextfield.setVisible(true);}
        }
    }

    /*
    // Update the visual stock in the shop
    public void updateStock() {
        Room shopRoom = Run.getShopRoom();
        shop.getChildren().clear();

        for (PurchasableItem i : shopRoom.getItems()) {
            Text btn = new Text(i.getName() + " - $" + i.getPrice());
            btn.setOnMouseClicked(this::buy);
            btn.setOnMouseEntered(this::highlightText);
            btn.setOnMouseExited(this::darkenText);
            shop.getChildren().add(btn);
        }
    }

     */
}
