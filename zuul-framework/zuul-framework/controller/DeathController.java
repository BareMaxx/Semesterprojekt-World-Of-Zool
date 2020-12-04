package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeathController extends GenericController {
    @FXML
    private Text title;

    @FXML
    private Text description;

    public void updateDeathScreen(String title, String desc) {
        this.title.setText(title);
        description.setText(desc);
    }
}
