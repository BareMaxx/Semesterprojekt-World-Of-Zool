package gameEngine;

import commands.Command;
import commands.CommandWord;
import item.Book;
import item.Item;
import player.Player;

import java.util.List;

//TODO: readBook method der sætter spillerens knowledge points ud fra bogen
//TODO: work method -> inc money
public class Child extends Game {
    protected final String SCHOOL_NAME = "school";

    public Child(Player p1) {
        super(p1, 60);
    }

    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case MONEY -> System.out.println("You have " + super.getPlayer().getMoney() + " gold");
            case READ -> {readBook();}
            case WORK -> work(7);
            default -> super.processCommand(command);
        }
    }

    //TODO: Player inventory removeItem method
    //Todo: Player inventory should be of Item type and not String
    //TODO: Player needs knowledgePoints attribute, and mutator and accessor methods

    // Read the first book in your inventory, if any
    public void readBook() {
        if (!inRoom(SCHOOL_NAME))
            return;

        List<Item> inventory = super.getPlayer().getInventory();
        boolean hasBook = false;
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Book) {
                hasBook = true;
                int bookKnowledgePoints = ((Book)item).getKNOWLEDGEPOINTS();
                super.getPlayer().incKnowledge(bookKnowledgePoints);
                super.getPlayer().removeInventoryItem(item);
                System.out.println("You read a book that gave you " + bookKnowledgePoints + " knowledge points");
                super.turns.decTurns(10);
                super.checkTurns();
                break;
            }
        }
        if (!hasBook) {
            System.out.println("You do not have a book in your inventory");
        }
    }
}
