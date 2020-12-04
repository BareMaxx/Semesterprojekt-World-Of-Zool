package item;

import gameplay.Turns;
import player.Player;

public class Protectors extends PurchasableItem {
    private final int MODIFIER;
    private final String USECASE;

    public Protectors(String name, int price, int MODIFIER, String USECASE) {
        super(price);
        this.name = name;
        this.MODIFIER = MODIFIER;
        this.USECASE = USECASE;
    }

    public int getModifier() {
        return MODIFIER;
    }

    public String getUseCase() {
        return USECASE;
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
