package worldofzuul;

public class Game 
{
    protected Parser parser;
    private Player p1;
    protected Turns turns;

    public Game(Player p1, Parser parser, int turns)
    {
        this.parser = new Parser();
        this.p1 = p1;
        this.turns = new Turns(turns);
        //new InitGame(p1);
    }

    public void play(){}

    public boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP -> printHelp();
            case GO -> {goRoom(command); turns.decTurns();}
            case QUIT -> wantToQuit = quit(command);
            case AGE -> System.out.println("You are " + p1.getAge() + " years old.");
            case INVENTORY -> p1.inventoryPrinter();
            case MONEY -> System.out.println("You have " + p1.getMoney() + " gold");
            case TAKE -> turns.decTurns();
            case WORK -> /*TODO: needs amount*/ turns.decTurns();
            case USE -> turns.decTurns();
            case BUY -> {buy(command); turns.decTurns();}
            case LOOK -> look();
            case SIT -> turns.decTurns();
            case TURNS -> System.out.println("You have " + turns.getTurns() + " turns left");
            default -> System.out.println("I don't know what you mean...");
        }
        checkTurns();
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

    public void checkTurns() {
        if(p1.getStage().equals("child") && turns.getTurns() <= 0) {
            p1.setStage("adult");
        } else if(p1.getStage().equals("adult") && turns.getTurns() <= 0) {
            p1.setStage("old");
        }
    }
}
