package gameEngine;

import player.Player;
import commands.Command;
import commands.CommandWord;

//TODO: work method -> inc money
public class Old extends Game {
    public Old(Player player) {
        super(player, 20);
    }

    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            default -> super.processCommand(command);
        }
    }

}
