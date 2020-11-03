package worldofzuul;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//TODO: readBook method der sætter spillerens knowledge points ud fra bogen
//TODO: work method -> inc money
public class Child extends Game {

    public Child() {
        //super();
    }

    //TODO: Player inventory removeItem method
    //Todo: Player inventory should be of Item type and not String
    //TODO: Book should have a getKnowledgePoints method
    //TODO: Player needs knowledgePoints attribute, and mutator and accessor methods
    public static  void readBook() {
        List<Item> inventory = super.getPlayer().getInventory();
        boolean hasBook = false;
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Book) {
                hasBook = true;
                int bookKnowledgePoints = item.getKnowledgePoints();
                super.getPlayer().incKnowledgePoints(bookKnowledgePoints);
                super.getPlayer().removeInventoryItem(item);
                System.out.println("You read a book that gave you " + bookKnowledgePoints + " knowledge points");
                break;
            }
        }
        if (!hasBook) {
            System.out.println("You do not have a book in your inventory");
        }
    }
}
