package controller;

import gameEngine.Run;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import player.Country;
import player.Player;

public class DeathController extends GenericController {
    @FXML
    private Text title;

    @FXML
    private Text stats;

    @FXML
    private Text deathCause;

    // Update the death screen according to cause of death and statistics
    public void updateDeathScreen() {
        Player player = Run.getRInstance().getPlayer();
        String cause = player.getDeathCause();
        Country country = player.getCountry();

        String st = "You were born in " + country.toString() + " as a " + player.getInitialEconomy().toString().toLowerCase() + " " +
                player.getGender().toString().toLowerCase() + "\n" +
                "and died at the age of " + player.getAge() + " as a " + player.getFamilyEconomy().toString().toLowerCase() + " " +
                player.getStage() + " with " + player.getMoney() + " gold and " + player.getKnowledge() + " knowledge";

        String dc = country.getStats(cause);

        title.setText("You have died of " + cause);
        stats.setText(st);
        deathCause.setText(dc);
    }
}