package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import gameEngine.Run;

public class GenericController {
    @FXML
    protected ImageView backgroundImage;

    @FXML
    void darkenText(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setStroke(Color.WHITE);
    }

    @FXML
    void highlightText(MouseEvent event) {
        Text text = (Text)event.getTarget();
        text.setStroke(Color.YELLOW);
    }

    @FXML
    void goOutside(MouseEvent event) throws Exception {
        changeScene("outside");
    }

    @FXML
    void changeScene(String room) {
        // application layer
        Run.getRInstance().processCommand("go " + room);

        if (Run.getRInstance().getPlayer().getAlive()) {
            String currentRoom = Run.getRInstance().getPlayer().getCurrentRoom().getName();

            switch (currentRoom) {
                case "home" -> Run.getPrimaryStage().setScene(ResourceController.getHomeData().scene);
                case "outside" -> Run.getPrimaryStage().setScene(ResourceController.getOutsideData().scene);
                case "startmenu" -> Run.getPrimaryStage().setScene(ResourceController.getStartmenuData().scene);
                case "hospital" -> Run.getPrimaryStage().setScene(ResourceController.getHospitalData().scene);
                case "school" -> Run.getPrimaryStage().setScene(ResourceController.getSchoolData().scene);
                case "work" -> Run.getPrimaryStage().setScene(ResourceController.getWorkData().scene);
                case "shop" -> Run.getPrimaryStage().setScene(ResourceController.getShopData().scene);
            }

            // Reparent inventory to whichever scene is on top
            ObservableList<Node> children = ((AnchorPane)Run.getPrimaryStage().getScene().getRoot()).getChildren();
            if (!children.contains(ResourceController.getOverlayData().scene))
                children.add(ResourceController.getOverlayData().scene);
        }
    }
}
