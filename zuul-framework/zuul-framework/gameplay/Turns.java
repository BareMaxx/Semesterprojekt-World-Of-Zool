package gameplay;

import controller.OverlayController;
import controller.ResourceController;

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
        incCounter(turns);
    }

    public void decTurns() {
        --this.turns;
        incCounter();

        // Update turns in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(turns);
    }

    public int getCounter() {
        return this.counter;
    }

    private void incCounter() {
        this.counter++;
    }
    private void incCounter(int i) {this.counter += i;}

    public void setCounter() {
        int j = 3 * (this.counter / 3);
        if (j > 0) {
            this.counter = this.counter - j;
        } else {
            this.counter = 0;
        }

    }

    public void setCounter(int count) {
        this.counter = count;
    }
}
