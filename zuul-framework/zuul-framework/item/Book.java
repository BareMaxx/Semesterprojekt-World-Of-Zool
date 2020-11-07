package item;

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
}
