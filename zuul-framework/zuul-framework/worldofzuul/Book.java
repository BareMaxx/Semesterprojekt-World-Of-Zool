package worldofzuul;

public class Book extends purchasableItem{

    private final int knowledgePoints;

    Book(String name, int price, int knowledgePoints)
    {
        super(price);
        this.name = name;
        this.knowledgePoints = knowledgePoints;
    }

    public int getKnowledgePoints() {
        return knowledgePoints;
    }
}
