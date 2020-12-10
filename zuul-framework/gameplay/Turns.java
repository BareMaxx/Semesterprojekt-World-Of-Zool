package gameplay;

import controller.OverlayController;
import controller.ResourceController;
import gameEngine.Child;
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
        int maxTurns = 60;
        if (game.getPlayer().getStage().equals("adult"))
            maxTurns = game.getPlayer().getAvgAge() * 3 - 60;

        ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(turns, maxTurns);
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

    // Set the counter
    public void setCounter(int count) {
        this.counter = count;
    }
}
