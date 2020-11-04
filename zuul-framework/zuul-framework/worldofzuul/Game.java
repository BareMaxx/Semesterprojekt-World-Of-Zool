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

    public void play(){}

    public boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case AGE -> System.out.println("You are " + p1.getAge() + " years old.");
            case INVENTORY -> p1.inventoryPrinter();
            case MONEY -> System.out.println("You have " + p1.getMoney() + " gold");
            case TAKE -> {}
            case USE -> {}
            case BUY -> buy(command);
            case LOOK -> look();
            case SIT -> sit();
            case STAND -> stand();
            case SLEEP -> sleep();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            default -> System.out.println("You can't do that at the current stage");
        }
        return wantToQuit;
    }
    public void work(int econStage){
        if(!p1.getCurrentRoom().getName().equals("Work")){
            System.out.println("You can't work here");
            return;
        }
        if(!p1.getCurrentRoom().isSitting()){
            System.out.println("You have to sit down");
            return;
        }

        //todo turns? age?
        //todo event accident
        int i = p1.getCountry().getMoney() * p1.getGender().getMoneyMulti() *
                p1.getFamilyEconomy().getMoneyMulti()/ econStage;
        p1.incMoney(i);
        System.out.println("You made " + i);
    }
    private void sleep(){
        if(!p1.getCurrentRoom().getName().equals("Home")){
            System.out.println("You have to be at home to sleep");
            return;
        }
        if(!p1.getCurrentRoom().isSitting()){
            System.out.println("You have to be sitting to sleep");
            return;
        }
        switch (p1.getStage()){
            case "child" -> {
                p1.setStage("adult");
                System.out.println("You are now an adult");
            }
            case "adult" -> {
                p1.setStage("old");
                System.out.println("You are now old");
            }
            case "old" -> {
                p1.setAlive(false);
                System.out.println("You are dead");
            }
        }
    }
    private void sit(){
        if(p1.getCurrentRoom().isSitting())
            System.out.println("You are already sitting");
        else {
            p1.getCurrentRoom().setSitting(true);
            System.out.println("You sat down");
        }
    }

    private void stand(){
        if(!p1.getCurrentRoom().isSitting())
            System.out.println("You are already standing");
        else{
            p1.getCurrentRoom().setSitting(false);
            System.out.println("You stood up");
        }
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
                    System.out.println("You bought " + s);
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
        System.out.println("Life is long and difficult");
        System.out.println("Too bad");
        System.out.println();
        System.out.println("Here's some commands");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(p1.getCurrentRoom().isSitting()){
            System.out.println("You have to stand up first");
            return;
        }

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
