package gameplay;

import player.Player;

public class Event {

    public String name = null;
    private String description;
    private final int PROBABILITYOFSUCCESS;   // (probability of the event being triggered)
    public RandomEngine randomEngine;
    public Player player;

    //Event(String name, int probabilityOfSuccess, Player player)
    Event(int probabilityOfSuccess, Player player)
    {
        //this.name = name;
        this.PROBABILITYOFSUCCESS = probabilityOfSuccess;
        randomEngine = new RandomEngine();
        this.player = player;
    }

    //public void runEvent()
    public boolean runEvent()
    {
        if (randomEngine.getOutcome(PROBABILITYOFSUCCESS, 100))
            //this.player.setAlive(false);
            return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPROBABILITYOFSUCCESS() {
        return PROBABILITYOFSUCCESS;
    }
}
