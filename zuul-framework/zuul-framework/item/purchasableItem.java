package item;

public class purchasableItem extends Item{

    private final int price;

    purchasableItem(int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
