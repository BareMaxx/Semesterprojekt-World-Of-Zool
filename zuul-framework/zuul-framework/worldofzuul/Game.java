package worldofzuul;

public class Game 
{
    protected Parser parser;
    private Player p1;

    public Game(Player p1, Parser parser)
    {
        this.parser = new Parser();
        this.p1 = p1;
        //new InitGame(p1);
    }

    public void play(){};

    public boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case AGE -> System.out.println("You are " + p1.getAge() + " years old.");
            case INVENTORY -> {//TODO: Player printInventory method
                p1.inventoryPrinter();
            }
            case MONEY -> System.out.println("You have " + p1.getMoney() + " gold");
            case TAKE -> {}
            case WORK -> {}
            case USE -> {}
            case BUY -> {buy(command);}
            case LOOK -> look();
            case SIT -> {}
            default -> System.out.println("I don't know what you mean...");
        }
        return wantToQuit;
    }

    private void buy(Command command){
        if(p1.getCurrentRoom().getName().equals("Shop")){
            if(!command.hasSecondWord()) {
                System.out.println("Buy what?");
                return;
            }

            String s = command.getSecondWord();


            Item i = p1.getCurrentRoom().getItem(s);
            if(i != null){
                if(p1.getMoney()>=i.getPrice()){
                    p1.addInventoryItem(i);
                    p1.getCurrentRoom().removeItem(i);
                    p1.decMoney(i.getPrice());
                }
            }
            else
                System.out.println("There is no " + s + " in the shop");
        }
    }

    private void look(){
        p1.getCurrentRoom().printStock();
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
            p1.setAlive(false);
            return true;
        }
    }

    public Player getPlayer() {
        return p1;
    }
}
