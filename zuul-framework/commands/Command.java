/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package commands;

// This class contains one CommandWord and one string from the Parser for simplicity
public class Command {
    private CommandWord commandWord;
    private String secondWord;

    // Initialize the class with a primary and a secondary word
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // Get the primary word
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    // Get the secondary word
    public String getSecondWord()
    {
        return secondWord;
    }

    // Return whether the command is unknown
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    // Whether the command has a second word
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

