package item;

public class Key extends Item {
    private final String KEYTYPE;   // keyType is effectively a room name

    Key(int price, String keyType) {
        super();
        this.name = "Key to " + keyType;
        this.KEYTYPE = keyType;
    }

    public String getKEYTYPE() {return this.KEYTYPE;}
    public boolean canUnlock(String roomName)
    {
        return (this.KEYTYPE.equals(roomName));
    }
}