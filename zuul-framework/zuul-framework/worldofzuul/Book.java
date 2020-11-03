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

    @Override
    public int getPrice() {
        return price;
    }

    public int getKnowledgePoints() {
        return knowledgePoints;
    }

    /*

    If the player class should be the one to manage the use of objects.
    It could be implemented as such:

    Player:

    public void readBook() {
        this.knowledge += this.inventory.book.read();
    }

    Book:

    public int read()
    {
        return this.knowledgePoints;
    }

     */
}
