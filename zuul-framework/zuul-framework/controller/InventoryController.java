package controller;

import gameEngine.Run;
import item.Item;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class InventoryController {
    @FXML
    private HBox inventory;

    public void updateInventory() {
        inventory.getChildren().clear();

        for (Item item : Run.getRInstance().getPlayer().getInventory()) {
            Text text = new Text(item.getName());
            text.setOnMouseClicked(event -> {
                String itemName = ((Text)event.getSource()).getText();
                Run.getRInstance().processCommand("use " + itemName);
            });
            inventory.getChildren().add(text);
        }
    }
}
