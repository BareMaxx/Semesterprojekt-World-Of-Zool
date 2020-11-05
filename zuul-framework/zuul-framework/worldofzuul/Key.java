package worldofzuul;

public class Key extends Item{

    public final int price;
    private final String keyType;   // keyType is effectively a room name

    Key(String name, int price, String keyType)
    {
        super(name);
        this.price = price;
        this.keyType = keyType;
    }

    public String getKeyType() {return this.keyType;}
    public boolean canUnlock(String roomName)
    {
        return (this.keyType.equals(roomName));
    }
}