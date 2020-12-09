package gameplay;

import controller.OverlayController;
import controller.ResourceController;
import item.Key;
import player.Player;
import java.util.ArrayList;
import java.util.Arrays;

public class Sickness extends Event {
    private ArrayList<String> ugNames = new ArrayList<>(Arrays.asList("tuberculosis", "cancer", "AIDS", "malaria"));
    private ArrayList<String> dkNames = new ArrayList<>(Arrays.asList("a heart disease", "cancer", "AIDS", "depression and you want to commit suicide"));
    private ArrayList<String> usNames = new ArrayList<>(Arrays.asList("a heart disease", "cancer", "AIDS", "diabetes"));
    private int turnLimit = randomEngine.getRandom(10,15);
    private int price = 0;

    // Initialize the sickness with a name and a healing price
    public Sickness(int probabilityOfSuccess, Player player) {
        super(probabilityOfSuccess, player);

        if (player.getSickness() != null)
            return;

        if (runEvent()) {
            setName();
            setPrice();
            System.out.println();
            System.out.println("Oh no, you were unlucky and you now have " + name + ", you have " + turnLimit +
                    " turns to get to the hospital and pay " + price + " gold to get healthy or you will die!");
            Key appointment = new Key("hospital", "appointment");
            player.addInventoryItem(appointment);
            System.out.println("You received an appointment to the hospital, use it to gain access.");

            // Show and update the GUI
            ((OverlayController) ResourceController.getOverlayData().controller).showSickTurns(turnLimit);
        }
    }

    // Return the amount of turns before death
    public int getTurnLimit() {
        return turnLimit;
    }

    // Decrease the turn and update GUI
    public void decTurnLimit(int i){
        turnLimit = turnLimit - i;
        ((OverlayController) ResourceController.getOverlayData().controller).updateSickTurns(turnLimit);
    }

    // Get the healing price
    public int getPrice() {
        return price;
    }

    // Set the healing price based on country
    private void setPrice() {
        switch (player.getCountry()) {
            case DANHEIM -> price = randomEngine.getRandom(0,1000);
            case VAKANNDA -> price = randomEngine.getRandom(200,3000);
            case WASHINGGEORGE -> price = randomEngine.getRandom(1000,30000);
        }
    }

    // Set the name based on country
    private void setName(){
        switch (player.getCountry()){
            case DANHEIM -> this.name = dkNames.get(randomEngine.getRandom(0,dkNames.size()-1));
            case VAKANNDA -> this.name = ugNames.get(randomEngine.getRandom(0,ugNames.size()-1));
            case WASHINGGEORGE -> this.name = usNames.get(randomEngine.getRandom(0,usNames.size()-1));
        }
    }
}
