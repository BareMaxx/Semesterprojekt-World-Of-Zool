package item;

public class Key extends Item {
    private final String KEYTYPE;   // keyType is effectively a room name

    public Key(int price, String keyType, String name) {
        super();
        //this.name = "Key to " + keyType;
        this.name = name;
        this.KEYTYPE = keyType;
    }

    public String getKEYTYPE() {return this.KEYTYPE;}
    public boolean canUnlock(String roomName)
    {
        return (this.KEYTYPE.equals(roomName));
    }
}