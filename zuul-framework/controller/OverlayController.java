package controller;

import gameEngine.Run;
import item.Item;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class OverlayController extends GenericController {
    @FXML
    private ListView inventory;

    @FXML
    private TextArea event;

    @FXML
    private Text moneyAmountText;

    @FXML
    private Text ageNumberText;

    @FXML
    private Text stageText;

    @FXML
    private Text genderText;

    @FXML
    private Text familyEconomyText;

    @FXML
    private Text sickTurnsText;

    @FXML
    private Text turnsUntilChangeText;

    @FXML
    private Text sickTurnsNumber;

    @FXML
    private ProgressBar turnsProgressbar;

    @FXML
    private Text turnsText;

    // Update age text-field
    public void updateAge() {
        ageNumberText.setText(Integer.toString(Run.getRInstance().getPlayer().getAge()));
    }

    // Update stage text-field
    public void increaseStage() {
        stageText.setText("Adult");
    }

    // Update money text-field
    public void updateMoney() {
        moneyAmountText.setText(Integer.toString(Run.getRInstance().getPlayer().getMoney()));
    }

    // Update gender text-field
    public void setGenderText(String gender) {
        genderText.setText(gender);
    }

    // Update economy text-field
    public void setFamilyEconomyText(String familyEconomy) {
        familyEconomyText.setText(familyEconomy);
    }

    // Update turns text-field and progress bar
    public void updateTurns(int turns){
        double progress;

        if (Run.getRInstance().getPlayer().getStage().equals("child")){
            progress = (double)turns / 60;
        } else {
            progress = (double)turns / 100;
        }

        turnsText.setText(Integer.toString(turns));
        turnsProgressbar.setProgress(progress);
    }

    // Show sickness text-field
    public void showSickTurns(int turnLimit) {
        sickTurnsText.setVisible(true);
        sickTurnsNumber.setVisible(true);
        sickTurnsNumber.setText(Integer.toString(turnLimit));
    }

    // Hide sickness text-field
    public void hideSickTurns() {
        sickTurnsText.setVisible(false);
        sickTurnsNumber.setVisible(false);
    }

    // Update sickness text-field
    public void updateSickTurns(int turns) {
        sickTurnsNumber.setText(Integer.toString(turns));
    }

    // Update "turns until" text-field
    public void updateTurnsUntilChangeText() {
        turnsUntilChangeText.setText("Turns left:");
    }

    // Update event-log text-field
    public void updateEventLog(String console) {
        event.setText(console);
        event.positionCaret(console.length());
    }

    // Update inventory
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