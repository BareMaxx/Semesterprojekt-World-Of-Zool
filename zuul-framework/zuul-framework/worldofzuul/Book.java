package worldofzuul;

public class Book extends Item{

    private final int price;
    private final int knowledgePoints;

    Book(String name, int price, int knowledgePoints)
    {
        super(name, "book");
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
