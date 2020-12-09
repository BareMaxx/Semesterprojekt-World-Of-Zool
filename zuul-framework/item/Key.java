package item;

import gameplay.Room;
import gameplay.Turns;
import player.Player;

// Keys are Items that can unlock certain Rooms
public class Key extends Item {
    private final String KEYTYPE; // keyType is effectively a room name

    // Initialize the Item with a name and a keyTpe
    public Key(String keyType, String name) {
        super();
        this.name = name;
        this.KEYTYPE = keyType;
    }

    // Return the type of the key
    public String getKEYTYPE() { return this.KEYTYPE; }

    // Return whether this Key can unlock the given Room
    public boolean canUnlock(String roomName)
    {
        return (this.KEYTYPE.equals(roomName));
    }

    // Use the key to unlock a door, if present
    @Override
    public void use(Player player, Turns turns) {
        Room room = player.getCurrentRoom().getExit(getKEYTYPE());

        // Uses the key if it matches a locked adjacent room
        if (room == null) {
            System.out.println();
            System.out.println("You can't use that here.");
        } else if (room.isLocked()) {
            room.unlock(this);
            player.removeInventoryItem(this);
        } else {
            System.out.println();
            System.out.println("This room is not locked. How did you get that key?");
        }
    }
}