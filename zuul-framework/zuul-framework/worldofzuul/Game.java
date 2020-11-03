package worldofzuul;

public class Game {
    protected Parser parser;
    private Player p1;

    public Game(Player p1, Parser parser) {
        this.parser = parser;
        this.p1 = p1;
        //new InitGame(p1);
    }

    public void play() {}

    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case QUIT -> quit(command);
            case AGE -> System.out.println("You are " + p1.getAge() + " years old.");
            case INVENTORY -> p1.inventoryPrinter();
            case MONEY -> System.out.println("You have " + p1.getMoney() + " gold");
            case TAKE -> {}
            case WORK -> {}
            case USE -> {}
            case BUY -> {}
            case LOOK -> {}
            case SIT -> {}
            default -> System.out.println("I don't know what you mean...");
        }
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = p1.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            p1.setCurrentRoom(nextRoom);
            System.out.println(p1.getCurrentRoom().getLongDescription());
        }
    }

    private void quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
        }
        else {
            p1.setAlive(false);
        }
    }

    public Player getPlayer() {
        return p1;
    }
}
