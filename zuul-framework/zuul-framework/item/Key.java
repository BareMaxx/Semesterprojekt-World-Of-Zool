package item;

public class Key extends Item {
    private final String keyType;   // keyType is effectively a room name

    Key(int price, String keyType) {
        super();
        this.name = "Key to " + keyType;
        this.keyType = keyType;
    }

    public boolean canUnlock(String roomName)
    {
        return (this.keyType.equals(roomName));
    }
}