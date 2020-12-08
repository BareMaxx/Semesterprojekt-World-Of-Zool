package gameplay;

import player.Player;

public class Event {
    protected String name = null;
    private final int PROBABILITYOFSUCCESS;
    public RandomEngine randomEngine;
    public Player player;

    Event(int probabilityOfSuccess, Player player) {
        this.PROBABILITYOFSUCCESS = probabilityOfSuccess;
        randomEngine = new RandomEngine();
        this.player = player;
    }

    public boolean runEvent() {
        if (randomEngine.getOutcome(PROBABILITYOFSUCCESS, 100))
            return true;
        return false;
    }

    public String getName() {
        return name;
    }
}
