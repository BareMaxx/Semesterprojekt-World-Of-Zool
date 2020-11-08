package gameplay;

import item.Key;
import player.Player;
import java.util.ArrayList;
import java.util.Arrays;

public class Sickness extends Event {
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("tuberculosis", "cancer", "AIDS"));
    private int turnLimit = randomEngine.getRandom(10,30);
    private int price = 0;

    public Sickness(int probabilityOfSuccess, Player player) {
        super(probabilityOfSuccess, player);

        if(player.getSickness() != null)
            return;

        if(runEvent()){
            this.name = names.get(randomEngine.getRandom(0,names.size()-1));
            setPrice();
            System.out.println("You have been infected with " + name + " you have " + turnLimit + " turns to get to the " +
                    "hospital and pay " + price + " gold to get healthy or you will die!");
            Key appointment = new Key(0, "hospital", "appointment");
            player.addInventoryItem(appointment);
            System.out.println("You received an appointment to the hospital, use it to gain access.");
        }
    }

    public int getTurnLimit() {
        return turnLimit;
    }
    public void decTurnLimit(int i){
        turnLimit = turnLimit - i;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(){
        switch (player.getCountry()){
            case DANHEIM -> price = randomEngine.getRandom(0,1000);
            case VAKANNDA -> price = randomEngine.getRandom(200,3000);
            case WASHINGGEORGE -> price = randomEngine.getRandom(1000,30000);
        }
    }
}
