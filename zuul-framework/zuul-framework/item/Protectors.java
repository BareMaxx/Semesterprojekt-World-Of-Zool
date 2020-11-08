package item;

public class Protectors extends PurchasableItem {
    private final int modifier;
    private final String useCase;

    public Protectors(String name, int price, int modifier, String useCase) {
        super(price);
        this.name = name;
        this.modifier = modifier;
        this.useCase = useCase;
    }

    public int getModifier() {
        return modifier;
    }

    public String getUseCase() {
        return useCase;
    }
}
