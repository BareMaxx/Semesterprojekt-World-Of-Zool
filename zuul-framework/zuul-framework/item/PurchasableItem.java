package item;

public class PurchasableItem extends Item {

    private final int price;

    PurchasableItem(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
