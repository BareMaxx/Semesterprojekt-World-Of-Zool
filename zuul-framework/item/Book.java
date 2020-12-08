package item;

import gameplay.Turns;
import player.Player;

// Books are Items that increments the player's knowledgePoints
public class Book extends PurchasableItem {
    private final int KNOWLEDGEPOINTS;

    // Initialize the book with a name, price and amount of knowledgePoints
    public Book(String name, int price, int KNOWLEDGEPOINTS) {
        super(price);
        this.name = name;
        this.KNOWLEDGEPOINTS = KNOWLEDGEPOINTS;
    }

    // Return knowledgepoints
    public int getKnowledgePoints() {
        return KNOWLEDGEPOINTS;
    }

    // Read the book and gain knowledgepoints
    @Override
    public void use(Player player, Turns turns) {
        if (!player.getCurrentRoom().getName().equals("school")) // Can only read in schools
            return;
        if (player.getStage().equals("adult")) // Only children can read. Everybody knows that
            return;

        int bookKnowledgePoints = getKnowledgePoints();
        if (player.getSickness() != null) {
            bookKnowledgePoints -= 50;
        }
        player.incKnowledge(getKnowledgePoints());
        player.removeInventoryItem(this);
        System.out.println("You read a book that gave you " + bookKnowledgePoints + " knowledge points");
        turns.decTurns(bookKnowledgePoints / 50);
    }
}
