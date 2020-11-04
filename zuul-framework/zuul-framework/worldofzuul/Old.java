package worldofzuul;
//TODO: work method -> inc money
public class Old extends Game {
    public Old(Player p1, Parser parser) {
        super(p1, parser);
    }

    public void play() {
        parser = new Parser();
        Command command = parser.getCommand();
        processCommand(command);
    }

    public boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case WORK -> System.out.println("You are too old to work");
            default -> super.processCommand(command);
        }
        return true;
    }

}
