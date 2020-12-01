package controller;

import gameEngine.Run;
import gameplay.Room;
import item.PurchasableItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ShopController extends GenericController {
    @FXML
    private HBox shop;

    @FXML
    void initialize() {
        Room shopRoom = Run.getShopRoom();

        for (PurchasableItem i : shopRoom.getItems()) {
            Button btn = new Button(i.getName());
            btn.setOnAction(event -> {
                try {
                    Run.getRInstance().processCommand("buy " + ((Button) event.getSource()).getText());
                    shop.getChildren().remove(event.getSource());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            shop.getChildren().add(btn);
        }
    }
}
