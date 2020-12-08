package controller;

import gameEngine.Run;
import gameplay.Room;
import item.PurchasableItem;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShopController extends GenericController {
    @FXML
    private VBox shop;

    @FXML
    void buy(MouseEvent event) {
        try {
            Run.getRInstance().processCommand("buy " + ((Text)event.getSource()).getText());
            shop.getChildren().remove(event.getSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
