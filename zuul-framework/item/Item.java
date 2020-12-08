package item;

import gameplay.Turns;
import player.Player;

public class Item {
    protected String name;

    // Initialize the Item with an name
    public Item() {
        this.name = "Item";
    }

    // Return the name of the Item
    public String getName() {
        return name;
    }

    // Use the Item
    public void use(Player player, Turns turns) { }
}
