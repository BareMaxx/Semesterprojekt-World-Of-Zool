package worldofzuul;

public class Key extends Item{

    public final int price;
    private final String keyType;   // keyType is effectively a room name

    Key(int price, String keyType)
    {
        super();
        this.price = price;
        this.keyType = keyType;
    }

    public boolean canUnlock(String roomName)
    {
        return (this.keyType.equals(roomName));
    }
}