package gameEngine;

import commands.Command;
import commands.CommandWord;
import player.Player;

public class Adult extends Game {
    // Make adult stage have 100 turns
    public Adult(Player player) {
        super(player, 200);
    }

    // Override work so that econStage is better for adults
    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case WORK -> super.work(5);
            default -> super.processCommand(command);
        }
    }
}
