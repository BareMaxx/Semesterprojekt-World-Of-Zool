package worldofzuul;

public class Turns {
    private int turns;

    public Turns(int turns) {
        this.turns = turns;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void decTurns(int turns) {
        this.turns -= turns;
    }

    public void decTurns() {
        this.turns--;
    }
}
