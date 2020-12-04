package item;

import gameplay.Turns;
import player.Player;

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

    @Override
    public void use(Player player, Turns turns) {
        switch (getUseCase()) {
            case "sickness" -> {
                player.decSickChance(getModifier());
                player.removeInventoryItem(this);
                System.out.println("You are now less likely to get sick");
            }
            case "dmg" -> {
                player.decDmgChance(getModifier());
                player.removeInventoryItem(this);
                System.out.println("You are now less likely to get injured");
            }
        }
    }
}
