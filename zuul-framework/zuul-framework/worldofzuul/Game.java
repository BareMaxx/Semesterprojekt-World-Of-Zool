package worldofzuul;

public class Game 
{
    private Parser parser;
    private Player p1;
        
    public Game()
    {
        parser = new Parser();
        p1 = new Player();
        new InitGame(p1);
    }

    public void play() 
    {
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case AGE:
                System.out.println("You are " + p1.getAge() + " years old.");
                break;

            case READ:
                Child.readBook();

                break;

            case INVENTORY:
                //player.printInventory();
                break;

            case MONEY:
                System.out.println("You have " + p1.getMoney() + " gold");
                break;

            case TAKE:
                break;

            case WORK:
                break;

            case USE:
                break;

            case BUY:
                //buyItem(command);
                break;

            case LOOK:
                break;

            case SIT:
                break;

            default:
                System.out.println("I don't know what you mean...");
                return false;
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
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

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    public Player getPlayer() {
        return p1;
    }
}
