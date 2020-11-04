package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;
    protected String name;

    Item()
    {
        this.ID = IDCounter++;
        this.name = "Item";
    }

    public String getName() {
        return name;
    }
}
