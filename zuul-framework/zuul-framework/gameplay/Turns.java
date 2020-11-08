package gameplay;

public class Turns {
    private int turns;
    private int counter = 0;

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
        --this.turns;
        incCounter();
    }

    public int getCounter() {
        return this.counter;
    }

    private void incCounter() {
        this.counter++;
    }
}
