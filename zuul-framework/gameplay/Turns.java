package gameplay;

import controller.OverlayController;
import controller.ResourceController;
import gameEngine.Game;

public class Turns {
    private int turns;
    private int counter = 0;
    private Game game;

    // Initialize turns
    public Turns(int turns, Game game) {
        this.turns = turns;
        this.game = game;
    }

    // Return the current amount of turns
    public int getTurns() {
        return turns;
    }

    // Set the turns
    public void setTurns(int turns) {
        this.turns = turns;
    }

    // Decrease turns and sick-turns and update GUI
    public void decTurns(int turns) {
        this.turns -= turns;
        incCounter(turns);
        if (game.getPlayer().getSickness() != null) {
            game.decrementSickTurns(turns);
        }

        // Update turns in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(turns);
    }

    // Get counter?
    public int getCounter() {
        return this.counter;
    }

    // Increment counter
    private void incCounter() {
        this.counter++;
    }
    private void incCounter(int i) {this.counter += i;}

    // Unused
    public void setCounter() {
        int j = 3 * (this.counter / 3);
        if (j > 0) {
            this.counter = this.counter - j;
        } else {
            this.counter = 0;
        }

    }

    // Set the counter
    public void setCounter(int count) {
        this.counter = count;
    }
}
