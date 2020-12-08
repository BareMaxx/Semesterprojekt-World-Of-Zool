package gameplay;

import controller.OverlayController;
import controller.ResourceController;
import gameEngine.Game;

public class Turns {
    private int turns;
    private int counter = 0;
    private Game game;

    public Turns(int turns, Game game) {
        this.turns = turns;
        this.game = game;
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
        if(game.getPlayer().getSickness() != null){
            game.decrementSickTurns(turns);
        }

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

    public void setCounter(int count) {
        this.counter = count;
    }
}
