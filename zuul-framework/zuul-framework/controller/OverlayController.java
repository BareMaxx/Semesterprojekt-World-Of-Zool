package controller;

import gameEngine.Run;
import item.Item;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class OverlayController extends GenericController {
    @FXML
    private ListView inventory;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea event;

    public void updateEventLog(String console) {
        event.setText(console);
        event.positionCaret(console.length());
    }

    public void updateInventory() {
        ObservableList items = inventory.getItems();
        items.clear();

        for (Item item : Run.getRInstance().getPlayer().getInventory()) {
            Text text = new Text(item.getName());
            text.setOnMouseClicked(event -> {
                String itemName = ((Text)event.getSource()).getText();
                Run.getRInstance().processCommand("use " + itemName);
            });
            items.add(text);
        }
    }
}
