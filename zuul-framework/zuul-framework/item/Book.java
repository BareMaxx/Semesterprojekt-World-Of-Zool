package item;

import gameplay.Turns;
import player.Player;

public class Book extends PurchasableItem {
    private final int knowledgePoints;

    public Book(String name, int price, int knowledgePoints) {
        super(price);
        this.name = name;
        this.knowledgePoints = knowledgePoints;
    }

    public int getKnowledgePoints() {
        return knowledgePoints;
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
