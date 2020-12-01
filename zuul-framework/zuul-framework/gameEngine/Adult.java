package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
import player.Player;

public class Adult extends Game {
    public Adult(Player p1, Parser parser) {
        super(p1, parser, 100);
    }

    public void play() {
        parser = new Parser();
        Command command = parser.getCommand();
        processCommand(command);
    }

    public boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case WORK -> super.work(5);
            default -> super.processCommand(command);
        }
        return true;
    }
}
