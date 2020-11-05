package gameEngine;

import commands.Command;
import commands.CommandWord;
import commands.Parser;
import gameplay.Room;
import gameplay.Turns;
import item.Item;
import item.PurchasableItem;
import item.Key;
import item.Book;
import player.Player;

import java.util.ArrayList;

public class Game {
    protected final String SHOP_NAME = "Shop";

    protected Parser parser;
    private Player p1;
    protected Turns turns;

    // Super constructor. Amount of turns decided by derived class
    public Game(Player p1, Parser parser, int turns) {
        this.parser = new Parser();
        this.p1 = p1;
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
            case AGE -> System.out.println("You are " + p1.getAge() + " years old.");
            case INVENTORY -> p1.inventoryPrinter();
            case MONEY -> System.out.println("You have " + p1.getMoney() + " gold");
            case TAKE -> turns.decTurns();
            case WORK -> /*TODO: needs amount*/ turns.decTurns();
            case USE -> {use(command); turns.decTurns();}
            case BUY -> {buy(command); turns.decTurns();}
            case LOOK -> look(command);
            case SIT -> turns.decTurns();
            case TURNS -> System.out.println("You have " + turns.getTurns() + " turns left");
            default -> System.out.println("I don't know what you mean...");
        }
        checkTurns();
        return wantToQuit;
    }

    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
        }

        else {
            String item = command.getSecondWord();

            for (Item i: p1.getInventory()) {
                if (i.getName().equals(item)) {
                    if  (i instanceof Book) {
                        System.out.println("You can't use a book, read it instead.");
                        //alternatively, using a book is the same as reading it
                        }
                    else if (i instanceof Key) {
                        Room room = p1.getCurrentRoom().getExit(((Key)i).getKeyType());

                        if (room == null) {
                            System.out.println("You can't use that here.");
                        }
                        else if (room.isLocked()){
                            room.unlock((Key)i);
                            //p1.removeInventoryItem(i);
                            //todo fix this so key gets removed
                        }
                        else {
                            System.out.println("This room is not locked. How did you get that key?");
                        }
                    }
                }
                else {
                    System.out.println("You have no item of that name.");
                }
            }
        }

        if (p1.getInventory().isEmpty()) {
            System.out.println("You have no items to use.");
        }
    }


    // Buy an item if you're in the "Shop" room
    private void buy(Command command) {
        if (p1.getCurrentRoom().getName().equals(SHOP_NAME)) {
            if (!command.hasSecondWord()) {
                System.out.println("Buy what?");
                return;
            }

            String s = command.getSecondWord();

            PurchasableItem i = p1.getCurrentRoom().getItem(s);
            if (i != null) {
                if (p1.getMoney() >= i.getPrice()) {
                    p1.addInventoryItem(i);
                    p1.getCurrentRoom().removeItem(i);
                    p1.decMoney(i.getPrice());
                    turns.decTurns();
                }
            }
            else
                System.out.println("There is no " + s + " in the shop");
        }
    }

    // Look at shoplist or look at items in the current room
    private void look(Command command) {
        if (p1.getCurrentRoom().getName().equals(SHOP_NAME)) {
            p1.getCurrentRoom().printStock();
        } else {
            if (command.hasSecondWord()) {
                System.out.println("You can't focus on anything in particular");
            } else {
                /*
                System.out.println("You take a closer look at your surroundings\nYou notice:");
                ArrayList<Item> items = getPlayer().getCurrentRoom().items;
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
                }

                 */
            }
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
        else if (nextRoom.isLocked()) {
            System.out.println("This door is locked.");
        }
        else {
            p1.setCurrentRoom(nextRoom);
            System.out.println(p1.getCurrentRoom().getLongDescription());
            turns.decTurns();
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
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
        if (p1.getStage().equals("child") && turns.getTurns() <= 0) {
            p1.setStage("adult");
        } else if (p1.getStage().equals("adult") && turns.getTurns() <= 0) {
            p1.setStage("old");
        }
    }
}
