package gameplay;

import player.Player;

public class Event {

    private String name;
    private String description;
    private final int probabilityOfSuccess;   // (probability of the event being triggered)
    private RandomEngine randomEngine;
    private Player player;

    Event(String name, int probabilityOfSuccess, Player player)
    {
        this.name = name;
        this.probabilityOfSuccess = probabilityOfSuccess;
        randomEngine = new RandomEngine();
        this.player = player;
    }

    public void runEvent()
    {
        if (randomEngine.getOutcome(probabilityOfSuccess))
            this.player.setAlive(false);
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

    public int getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }
}
