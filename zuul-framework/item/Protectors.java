package item;

import gameplay.Turns;
import player.Player;

// Protectors are Items that lower the player's chance of Sickness or WorkDMG
public class Protectors extends PurchasableItem {
    private final int MODIFIER;
    private final String USECASE;

    // Initialize the Item with a name, modifier and usecase
    public Protectors(String name, int price, int modifier, String usecase) {
        super(price);
        this.name = name;
        this.MODIFIER = modifier;
        this.USECASE = usecase;
    }

    // Return the modifier
    public int getModifier() {
        return MODIFIER;
    }

    // Return the useCase
    public String getUseCase() {
        return USECASE;
    }

    // Wear the protector
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
