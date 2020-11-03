package worldofzuul;
//TODO: readBook method der sætter spillerens knowledge points ud fra bogen
//TODO: work method -> inc money
public class Adult extends Game {
    public Adult(Player p1, Parser parser) {
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
            case WORK -> System.out.println("Working, yes");
            default -> super.processCommand(command);
        }
        return true;
    }
}
