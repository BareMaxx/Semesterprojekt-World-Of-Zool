package commands;

public enum CommandWord {
    GO("go"), QUIT("quit"), UNKNOWN("?"),
    WORK("work"), BUY("buy"), USE("use"),
    SLEEP("sleep"), HEAL("heal");

    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString() { return commandString; }
}
