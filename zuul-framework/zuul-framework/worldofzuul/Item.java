package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;
    private String name;

    Item()
    {
        this.ID = IDCounter++;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return -1;
    }
    public int getKnowledgePoints() {return -1;}
}
