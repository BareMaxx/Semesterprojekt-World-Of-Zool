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

        String st = "You were born in " + player.getCountry().toString() + "\n" +
                "as a " + player.getGender() + "\n" +
                "and died at the age of " + player.getAge() + "\n" +
                "as a " + player.getFamilyEconomy().toString() + " " + player.getStage() + "\n" +
                "with " + player.getMoney() + " money\n" +
                "";
        String dc = "";

        title.setText("You have died of " + cause);
        stats.setText(st);
        deathCause.setText(dc);
    }
}
