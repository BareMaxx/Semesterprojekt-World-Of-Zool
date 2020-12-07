package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import player.Player;

public class DeathController extends GenericController {
    @FXML
    private Text title;

    @FXML
    private Text stats;

    @FXML
    private Text deathCause;

    public void updateDeathScreen() {
        Player player = Run.getRInstance().getPlayer();
        String cause = player.getDeathCause();

        String st = "You were born in " + player.getCountry().toString() + " as a " + player.getGender().toString().toLowerCase() + "\n" +
                "and died at the age of " + player.getAge() + " as a " + player.getFamilyEconomy().toString().toLowerCase() + " " +
                player.getStage() + " with " + player.getMoney() + " money and " + player.getKnowledge() + " knowledge";

        String dc = "The chance of dying of " + cause + " in " + player.getCountry().toString() + " is ";

        title.setText("You have died of " + cause);
        stats.setText(st);
        deathCause.setText(dc);
    }
}
