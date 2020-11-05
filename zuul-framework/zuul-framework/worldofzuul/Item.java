package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;
    protected String name;

    Item(String name) {
        this.ID = IDCounter++;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
