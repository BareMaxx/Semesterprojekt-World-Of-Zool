package item;

import gameplay.Room;
import player.Player;

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

    @Override
    public void use(Player player) {
        Room room = player.getCurrentRoom().getExit(getKEYTYPE());

        if (room == null) {
            System.out.println("You can't use that here.");
        } else if (room.isLocked()) {
            room.unlock(this);
            player.removeInventoryItem(this);
        } else {
            System.out.println("This room is not locked. How did you get that key?");
        }
    }
}