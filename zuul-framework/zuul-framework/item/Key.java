package item;

public class Key extends Item {
    private final String KEYTYPE;   // keyType is effectively a room name

    Key(int price, String KEYTYPE) {
        super();
        this.name = "Key to " + KEYTYPE;
        this.KEYTYPE = KEYTYPE;
    }

    public String getKeyType() {return this.KEYTYPE;}
    public boolean canUnlock(String roomName)
    {
        return (this.KEYTYPE.equals(roomName));
    }
}