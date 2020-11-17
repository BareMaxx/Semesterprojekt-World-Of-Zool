package item;

import player.Player;

public class Book extends PurchasableItem {

    private final int KNOWLEDGEPOINTS;

    public Book(String name, int price, int knowledgePoints) {
        super(price);
        this.name = name;
        this.KNOWLEDGEPOINTS = knowledgePoints;
    }

    public int getKNOWLEDGEPOINTS() {
        return KNOWLEDGEPOINTS;
    }

    @Override
    public void use(Player player) {
        System.out.println("You can't use a book, read it instead.");
    }
}
