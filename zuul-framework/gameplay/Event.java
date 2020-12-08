package gameplay;

import player.Player;

public class Event {
    protected String name = null;
    private final int PROBABILITYOFSUCCESS;
    public RandomEngine randomEngine;
    public Player player;

    // Instantiate the event with a given probability
    Event(int probabilityOfSuccess, Player player) {
        this.PROBABILITYOFSUCCESS = probabilityOfSuccess;
        randomEngine = new RandomEngine();
        this.player = player;
    }

    // Determine whether the event should happen or not
    public boolean runEvent() {
        if (randomEngine.getOutcome(PROBABILITYOFSUCCESS, 100))
            return true;
        return false;
    }

    // Return the name of the event
    public String getName() {
        return name;
    }
}
