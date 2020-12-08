package commands;
import java.util.HashMap;


public class CommandWords {
    private HashMap<String, CommandWord> validCommands;

    // Initialize a list of all CommandWords
    public CommandWords() {
        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    // Get the CommandWord given a String
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    // Return whether the specified string is in the list of commands
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    // Show a list of all commands
    public void showAll()  {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
