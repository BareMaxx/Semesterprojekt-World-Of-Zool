package item;

import gameplay.Turns;
import player.Player;

public class Book extends PurchasableItem {
    private final int KNOWLEDGEPOINTS;

    public Book(String name, int price, int KNOWLEDGEPOINTS) {
        super(price);
        this.name = name;
        this.KNOWLEDGEPOINTS = KNOWLEDGEPOINTS;
    }

    public int getKnowledgePoints() {
        return KNOWLEDGEPOINTS;
    }

    @Override
    public void use(Player player, Turns turns) {
        if (!player.getCurrentRoom().getName().equals("school"))
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
