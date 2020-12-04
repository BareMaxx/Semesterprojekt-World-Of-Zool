package item;

public class PurchasableItem extends Item {

    private final int PRICE;

    PurchasableItem(int PRICE) {
        this.PRICE = PRICE;
    }

    public int getPrice() {
        return PRICE;
    }
}
