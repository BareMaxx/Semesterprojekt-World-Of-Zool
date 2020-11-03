package worldofzuul;

public class Book extends Item{

    private final int price;
    private final int knowledgePoints;

    Book(int price, int knowledgePoints)
    {
        super();
        this.price = price;
        this.knowledgePoints = knowledgePoints;
    }

    public int getPrice() {
        return price;
    }

    public int getKnowledgePoints() {
        return knowledgePoints;
    }
}
