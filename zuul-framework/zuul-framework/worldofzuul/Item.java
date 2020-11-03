package worldofzuul;

public class Item {

    public int ID;
    private static int IDCounter;
    private String name;
    private String type;

    Item()
    {
        this.ID = IDCounter++;
    }
    Item(String name, String type) {
        this.ID = IDCounter++;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }
    //Make price an attribute in this superclass instead of the subclasses
    //In this way, we do not need multiple getPrice()
    public int getPrice() {
        return -1;
    }
    public String getName() {
        return name;
    }
}
