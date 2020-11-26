package commands;

public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"),
    LOOK("look"), INVENTORY("inventory"), MONEY("money"),
    SIT("sit"), WORK("work"), READ("read"), BUY("buy"),
    USE("use"), AGE("age"), TURNS("turns"), STAND("stand"),
    SLEEP("sleep"), HEAL("heal"), SICK("sick");

    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString() { return commandString; }
}
