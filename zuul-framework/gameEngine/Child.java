package gameEngine;

import commands.Command;
import commands.CommandWord;
import player.Player;

public class Child extends Game {
    // Initialize child with 60 turns
    public Child(Player p1) {
        super(p1, 60);
    }

    // Override work so that econStage is worse for children
    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case WORK -> work(7);
            default -> super.processCommand(command);
        }
    }
}
