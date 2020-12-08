package gameplay;

import item.PurchasableItem;
import item.Key;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room {
    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<PurchasableItem> stock;
    private boolean locked;

    // Initialize the room with a name, a description and whether it's locked
    public Room(String name, String description, boolean locked) {
        this.setName(name);
        this.description = description;
        this.locked = locked;
        exits = new HashMap<>();
        stock = new ArrayList<>();
    }

    // Add a single item to the stock
    public void setItem(PurchasableItem i) {
        stock.add(i);
    }

    // Return the first item with the given name or null if not found
    public PurchasableItem getItem(String name) {
        for (PurchasableItem i : stock) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        System.out.println("Item not in shop");
        return null;
    }

    // Remove the given item from the stock
    public void removeItem(PurchasableItem i) {
        stock.remove(i);
    }

    // Return the shop's stock
    public ArrayList<PurchasableItem> getItems() {
        return stock;
    }

    // Set an exit to the room
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // Get the longer description of the room
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    // Return all the exits to the current room
    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    // Get the exit from a String
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    // Return the name of this room
    public String getName() {
        return name;
    }

    // Set the name of this room
    public void setName(String name) {
        this.name = name;
    }

    // Return whether this room is locked
    public boolean isLocked() { return this.locked; }

    // Unlock the room if the key matches
    public void unlock(Key key) {
        if (key.canUnlock(this.getName())) {
            this.locked = false;
            System.out.println("You have unlocked " + this.getName());
        }
        else
            System.out.println("You can't do that here.");
    }

    // Lock the room again
    public void lock() {
        this.locked = true;
    }
}

