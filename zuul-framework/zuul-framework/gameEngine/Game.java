package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
import gameplay.Room;
import gameplay.Sickness;
import gameplay.Turns;
import gameplay.WorkDMG;
import item.*;
import player.Player;

public class Game {
    protected final String SHOP_NAME = "shop";
    
    protected Parser parser;
    private Player player;
    protected Turns turns;

    // Super constructor. Amount of turns decided by derived class
    public Game(Player player, Parser parser, int turns) {
        this.parser = new Parser();
        this.player = player;
        this.turns = new Turns(turns);
    }

    // Will be overriden by Child, Adult and Old
    public void play() {}

    // Processes commands. Derived classes have their own special overrides
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case AGE -> System.out.println("You are " + player.getAge() + " years old.");
            case INVENTORY -> player.inventoryPrinter();
            case MONEY -> System.out.println("You have " + player.getMoney() + " gold");
            case USE -> {use(command); turns.decTurns();}
            case BUY -> {buy(command); turns.decTurns();}
            case LOOK -> look(command);
            case TURNS -> System.out.println("You have " + turns.getTurns() + " turns left");
            case SIT -> {sit(); turns.decTurns();}
            case STAND -> {stand(); turns.decTurns();}
            case SLEEP -> sleep();
            case HEAL -> heal();
            case SICK -> sick();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            default -> System.out.println("You can't do that at the current stage");
        }
        checkTurns();
        endTurn();
        return wantToQuit;
    }

    private void endTurn(){
        if(player.getSickness()!= null) {
            player.getSickness().decTurnLimit(1);
            if(player.getSickness().getTurnLimit() == 0)
                player.setAlive(false);
        }
    }

    private void sick(){
        if(player.getSickness() == null)
            System.out.println("You are healthy");
        else
            System.out.println("You have been infected with " + player.getSickness().getName() + " you have " +
                    (player.getSickness().getTurnLimit() - 1) + " turns to get to the hospital and pay " +
                    player.getSickness().getPrice() + " gold to get healthy or you will die!");
    }

    private void heal(){
        if(!inPlace("hospital"))
            return;
        if(player.getSickness() != null){
            if(player.getMoney() >= player.getSickness().getPrice()){
                player.decMoney(player.getSickness().getPrice());
                player.setSickness(null);
                System.out.println("You have been healed");
                player.getCurrentRoom().lock();
            }
            else
                System.out.println("You don't have enough money to do that.");
        }
        else if (player.getDmg() != null){
            if(player.getMoney() >= player.getDmg().getPrice()){
                player.decMoney(player.getDmg().getPrice());
                player.setDmg(null);
                System.out.println("You have been healed");
                player.getCurrentRoom().lock();
            }
            else
                System.out.println("You don't have enough money to do that.");
        }
        else
            System.out.println("There is nothing to do here. You are healthy. Leave!");
    }

    public boolean inPlace(String room){
        if (!player.getCurrentRoom().getName().equals(room)) {
            System.out.println("You have to be at " + room + " to do that");
            return false;
        }
        if (!player.getCurrentRoom().isSitting()) {
            System.out.println("You have to sit down");
            return false;
        }
        return true;
    }

    public void work(int econStage) {
        if(!inPlace("work"))
            return;

        //todo turns? age?
        if(player.getSickness() != null){
            System.out.println("You can't work while sick");
            return;
        }
        if(player.getDmg() != null){
            System.out.println("You can't work while injured");
            return;
        }
        int i = player.getCountry().getMoney() * player.getGender().getMoneyMulti() *
                player.getFamilyEconomy().getMoneyMulti() / econStage;
        player.incMoney(i);
        System.out.println("You made " + i);
        randomSickEvent(player.getSickChance() * 2);
        randomDmgEvent(player.getDmgChance() * 2);
    }
    
    private void sleep() {
        if(!inPlace("home"))
            return;
        switch (player.getStage()) {
            case "child" -> {
                player.setStage("adult");
                System.out.println("You are now an adult");
            }
            case "adult" -> {
                player.setStage("old");
                System.out.println("You are now old");
            }
            case "old" -> {
                player.setAlive(false);
                System.out.println("You are dead");
            }
        }
    }
    
    private void sit() {
        if (player.getCurrentRoom().isSitting())
            System.out.println("You are already sitting");
        else {
            player.getCurrentRoom().setSitting(true);
            System.out.println("You sat down");
        }
    }

    private void stand() {
        if (!player.getCurrentRoom().isSitting())
            System.out.println("You are already standing");
        else {
            player.getCurrentRoom().setSitting(false);
            System.out.println("You stood up");
        }
    }

    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
        } else {
            String item = command.getSecondWord();

            for (Item i: player.getInventory()) {
                if (i.getName().equals(item)) {
                    if  (i instanceof Book) {
                        System.out.println("You can't use a book, read it instead.");
                        return;
                        //alternatively, using a book is the same as reading it
                        }
                    else if (i instanceof Key) {
                        Room room = player.getCurrentRoom().getExit(((Key) i).getKEYTYPE());

                        if (room == null) {
                            System.out.println("You can't use that here.");
                            return;
                        }
                        else if (room.isLocked()){
                            room.unlock((Key) i);
                            player.removeInventoryItem(i);
                            return;
                        }
                        else {
                            System.out.println("This room is not locked. How did you get that key?");
                            return;
                        }
                    }
                    else if (i instanceof Protectors){
                        switch (((Protectors) i).getUseCase()){
                            case "sickness" -> {
                                player.decSickChance(((Protectors) i).getModifier());
                                player.removeInventoryItem(i);
                                System.out.println("You are now less likely to get sick");
                                return;
                            }
                            case "dmg" -> {
                                player.decDmgChance(((Protectors) i).getModifier());
                                player.removeInventoryItem(i);
                                System.out.println("You are now less likely to get injured");
                                return;
                            }
                        }
                    }
                }
            }
            System.out.println("You have no item of that name.");
        }

        if (player.getInventory().isEmpty()) {
            System.out.println("You have no items to use.");
        }
    }

    // Buy an item if you're in the "Shop" room
    private void buy(Command command) {
        if (player.getCurrentRoom().getName().equals(SHOP_NAME)) {
            if (!command.hasSecondWord()) {
                System.out.println("Buy what?");
                return;
            }

            String s = command.getSecondWord();

            PurchasableItem i = player.getCurrentRoom().getItem(s);
            if (i != null) {
                if (player.getMoney() >= i.getPrice()) {
                    player.addInventoryItem(i);
                    player.getCurrentRoom().removeItem(i);
                    player.decMoney(i.getPrice());
                    turns.decTurns();

                    System.out.println("You bought " + s);
                    player.decMoney((i).getPrice());
                    randomSickEvent(player.getSickChance() * 2);
                }
            }
            else
                System.out.println("There is no " + s + " in the shop");
        }
    }

    // Look at shoplist or look at items in the current room
    private void look(Command command) {
        if (player.getCurrentRoom().getName().equals(SHOP_NAME)) {
            player.getCurrentRoom().printStock();
        }
        else if (player.getCurrentRoom().getName().equals("hospital")) {
            if(player.getSickness() != null)
                System.out.println("You have " + player.getSickness().getName() + ", it will cost you " +
                    player.getSickness().getPrice() + " to get healed. Type heal to get healed");
            else if (player.getDmg() != null)
                System.out.println("It will cost you " + player.getDmg().getPrice() + " to get healed. " +
                        "Type heal to get healed");
            else
                System.out.println("There is nothing to do here. You are healthy. Leave!");
        }else {
            if (command.hasSecondWord()) {
                System.out.println("You can't focus on anything in particular");
            } else {
                System.out.println("You take a closer look at your surroundings\nYou notice:");
                /*ArrayList<Item> items = getPlayer().getCurrentRoom().items;
                ArrayList<Item> objects = getPlayer().getCurrentRoom().objects;
                if (items.isEmpty() && objects.isEmpty()) {
                    System.out.println("\tnothing");
                } else {
                    for (Item i : items) {
                        System.out.println("\t" + i.getName());
                    }
                    for (Item o : objects) {
                        System.out.println("\t" + o.getName());
                    }
                }*/
            }
        }
    }

    private void printHelp() {
        System.out.println("Life is long and difficult");
        System.out.println("Too bad");
        System.out.println();
        System.out.println("Here's some commands");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (player.getCurrentRoom().isSitting()) {
            System.out.println("You have to stand up first");
            return;
        }

        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom.isLocked()) {
            System.out.println("This door is locked.");
        }
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            turns.decTurns();
            randomSickEvent(player.getSickChance());
            randomDmgEvent(player.getDmgChance());
        }
    }
    private void randomSickEvent(int probability){
        Sickness s = new Sickness(probability, player);
        if(s.name != null)
            player.setSickness(s);
    }
    private void randomDmgEvent(int probability){
        WorkDMG dmg = new WorkDMG(probability, player);
        if(dmg.name != null)
            player.setDmg(dmg);
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            player.setAlive(false);
            return true;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void checkTurns() {
        if (player.getStage().equals("child") && turns.getTurns() <= 0) {
            player.setStage("adult");
            System.out.println("You grew up to be an adult");
        } else if (player.getStage().equals("adult") && turns.getTurns() <= 0) {
            player.setStage("old");
            System.out.println("You grew up to be old");
        }
    }
}
