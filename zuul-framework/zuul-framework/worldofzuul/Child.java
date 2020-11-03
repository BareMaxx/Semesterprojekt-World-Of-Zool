package worldofzuul;

import java.util.List;

//TODO: readBook method der sætter spillerens knowledge points ud fra bogen
//TODO: work method -> inc money
public class Child extends Game {
    public Child(Player p1, Parser parser) {
        super(p1, parser);
    }
    public void play(){
        parser = new Parser();
        Command command = parser.getCommand();
        processCommand(command);
    }
    public boolean processCommand(Command command){
        CommandWord commandWord = command.getCommandWord();
        switch(commandWord) {
            case MONEY:
                System.out.println("You are a child but will now grow to an adult to showcase how the code should work");
                super.getPlayer().setStage("adult");
                break;
            case READ:
                //Can't be accessed from super class?
                readBook();
                break;
            default:
                super.processCommand(command);
        }
        return true;
    }

    //TODO: Player inventory removeItem method
    //Todo: Player inventory should be of Item type and not String
    //TODO: Book should have a getKnowledgePoints method
    //TODO: Player needs knowledgePoints attribute, and mutator and accessor methods

    public void readBook() {
        Item book = new Book(100, 35);
        List<Item> inventory = super.getPlayer().getInventory();
        boolean hasBook = false;
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Book) {
                hasBook = true;
                int bookKnowledgePoints = item.getKnowledgePoints();
                //super.getPlayer().incKnowledgePoints(bookKnowledgePoints);
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
