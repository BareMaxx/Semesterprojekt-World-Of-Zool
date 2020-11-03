package worldofzuul;

public class Event {

    private String name;
    private String description;
    private final int probabilityOfSuccess;   // (probability of the event being triggered)
    private Player player;

    Event(String name, int probabilityOfSuccess, Player player)
    {
        this.name = name;
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.player = player;
    }

    public void runEvent()
    {
        
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
