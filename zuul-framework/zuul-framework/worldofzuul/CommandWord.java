package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"),
    TAKE("take"), LOOK("look"), INVENTORY("inventory"),
    MONEY("money"), SIT("sit"), WORK("work"), READ("read"),
    BUY("buy"), USE("use"), AGE("age");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString() { return commandString; }
}
