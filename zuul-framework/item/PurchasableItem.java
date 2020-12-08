package item;

// An Item that can be purchased through the Shop-room
public class PurchasableItem extends Item {
    private final int PRICE;

    // Initialize the Item with a price
    PurchasableItem(int PRICE) {
        this.PRICE = PRICE;
    }

    // Return the price of the Item
    public int getPrice() {
        return PRICE;
    }
}
