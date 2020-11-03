package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;

    Item()
    {
        this.ID = IDCounter++;
    }
}
