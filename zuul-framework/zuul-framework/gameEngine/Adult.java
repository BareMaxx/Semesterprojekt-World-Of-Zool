package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
import player.Player;

public class Adult extends Game {
    public Adult(Player player) {
        super(player, 100);
    }

    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case WORK -> super.work(5);
            default -> super.processCommand(command);
        }
    }
}
