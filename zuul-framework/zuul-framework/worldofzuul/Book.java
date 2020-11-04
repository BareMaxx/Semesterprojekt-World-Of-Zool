package worldofzuul;

public class Book extends purchasableItem{

    private final int knowledgePoints;

    Book(int price, int knowledgePoints)
    {
        super(price);
        this.name = "Book";
        this.knowledgePoints = knowledgePoints;
    }

    public int getKnowledgePoints() {
        return knowledgePoints;
    }
}
