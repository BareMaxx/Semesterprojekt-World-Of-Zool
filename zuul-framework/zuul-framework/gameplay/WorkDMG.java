package gameplay;

import item.Key;
import player.Player;

public class WorkDMG extends Event {
    private int price = 0;

    public WorkDMG(int probabilityOfSuccess, Player player) {
        super(probabilityOfSuccess, player);

        if(player.getDmg() != null)
            return;

        if(runEvent()){
            this.name = "broken leg"; // this is not used, it is only here because we check if (name == null) to see if successful
            setPrice();
            System.out.println("You had an accident, and received an injury. You can't work while injured. Go to the " +
                    "hospital and pay " + price + " gold to get healed.");
            Key appointment = new Key(0, "hospital", "appointment");
            player.addInventoryItem(appointment);
            System.out.println("You received an appointment to the hospital, use it to gain access.");
        }
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
