package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;

    Item()
    {
        this.ID = IDCounter++;
    }

    public int getPrice() {
        return -1;
    }
    public int getKnowledgePoints() {return -1;}
}
