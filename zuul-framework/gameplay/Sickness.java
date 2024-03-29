package gameplay;

import controller.OverlayController;
import controller.ResourceController;
import item.Key;
import player.Player;
import player.Country;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Sickness extends Event {
    private ArrayList<String> ugNames = new ArrayList<>(Arrays.asList("tuberculosis", "cancer", "AIDS", "malaria"));
    private ArrayList<String> dkNames = new ArrayList<>(Arrays.asList("a heart disease", "cancer", "AIDS", "depression and you want to commit suicide"));
    private ArrayList<String> usNames = new ArrayList<>(Arrays.asList("a heart disease", "cancer", "AIDS", "diabetes"));
    private int turnLimit = randomEngine.getRandom(10,15);
    private int price = 0;

    /*
    the number value in the hashmap is the chance of getting the illness out of 1000. When a Sickness Event
    is called, the Sickness() Constructor will choose a random name fom the list of available sicknesses,
    and it will then call the getChance() method, which will check whether or not the illness will happen.
    Since the chance of getting ill is already the desired chance, the getChance() method will call itself
    with a new, random name until the player gets sick. The chances are approximations of real data from the
    reference areas, but have been slightly exaggerated for the purpose of the game.
    */
    private HashMap<String, Integer> sickChanceUg = new HashMap<>() {{
        put("tuberculosis", 333);
        put("cancer", 333);
        put("AIDS", 333);
        put("malaria", 333);
    }};
    private HashMap<String, Integer> sickChanceDk = new HashMap<>() {{
        put("a heart disease", 150);
        put("cancer", 250);
        put("AIDS", 1);
        put("depression and you want to commit suicide", 100);
    }};
    private HashMap<String, Integer> sickChanceUs = new HashMap<>() {{
        put("a heart disease", 250);
        put("cancer", 10);
        put("AIDS", 3);
        put("diabetes", 200);
    }};

    public Sickness(int probabilityOfSuccess, Player player) {
        super(probabilityOfSuccess, player);

        if (player.getSickness() != null)
            return;

        // If the event runs, it gives the player an illness with a random name, a random price to heal
        // based on country, and a random amount of turns till the player dies.
        if (runEvent()) {
            setName(player.getCountry());
            if (!getChance(player.getCountry(), this.name)) {return;}

            setPrice();
            System.out.println();
            System.out.println("Oh no, you were unlucky and you now have " + name + ", you have " + turnLimit +
                    " turns to get to the hospital and pay " + price + " gold to get healthy or you will die!");
            Key appointment = new Key("hospital", "appointment");
            player.addInventoryItem(appointment);
            System.out.println("You received an appointment to the hospital, use it to gain access.");
            ((OverlayController) ResourceController.getOverlayData().controller).showSickTurns(turnLimit);
        }
    }

    // Gets amount of turns till you die
    public int getTurnLimit() {
        return turnLimit;
    }
    public void decTurnLimit(int i){
        turnLimit = turnLimit - i;
        ((OverlayController) ResourceController.getOverlayData().controller).updateSickTurns(turnLimit);
    }

    // Randomize the name based on the HashMap
    private boolean getChance(Country country, String name) {
        switch (country){
            case VAKANNDA -> {
                if (randomEngine.getOutcome(sickChanceUg.get(name), 1000)) {
                    return true;
                } else {
                    setName(country);
                    return getChance(country, this.name);
                }
            }
            case DANHEIM -> {
                if (randomEngine.getOutcome(sickChanceDk.get(name), 1000)) {
                    return true;
                } else {
                    setName(country);
                    return getChance(country, this.name);
                }
            }
            case WASHINGGEORGE -> {
                if (randomEngine.getOutcome(sickChanceUs.get(name), 1000)){
                    return true;
                } else {
                    setName(country);
                    return getChance(country, this.name);
                }
            }
            default -> {return false;}
        }
    }

    public int getPrice() {
        return price;
    }

    // Sets the price of getting healed
    private void setPrice(){
        switch (player.getCountry()) {
            case DANHEIM -> price = randomEngine.getRandom(0,1000);
            case VAKANNDA -> price = randomEngine.getRandom(200,3000);
            case WASHINGGEORGE -> price = randomEngine.getRandom(1000,30000);
        }
    }

    // Sets the name of the sickness
    private void setName(Country country){
        switch (country){
            case DANHEIM -> this.name = dkNames.get(randomEngine.getRandom(0,dkNames.size()-1));
            case VAKANNDA -> this.name = ugNames.get(randomEngine.getRandom(0,ugNames.size()-1));
            case WASHINGGEORGE -> this.name = usNames.get(randomEngine.getRandom(0,usNames.size()-1));
        }
    }
}
