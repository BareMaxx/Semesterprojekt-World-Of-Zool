package commands;

import java.util.Scanner;

public class Parser  {
    private CommandWords commands;

    // Initialize the parser with a list of commands
    public Parser() {
        commands = new CommandWords();
    }

    // Instantiate a Command given a String
    public Command getCommand(String inputLine) {
        String word1 = null;
        String word2 = null;

        // Get the primary and secondary word
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    // Show a list of commands. Unused
    public void showCommands()
    {
        commands.showAll();
    }
}
