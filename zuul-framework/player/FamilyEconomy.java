package player;

// Enumeration of the different economic statuses the player can have,
// along with a corresponding starting money multiplier.
public enum FamilyEconomy {
    RICH(10),
    MIDDLECLASS(7),
    POOR(5);

    private int moneyMulti;

    // Initialize money-multiplier
    FamilyEconomy(int moneyMulti){
        this.moneyMulti = moneyMulti;
    }

    // Return the multiplier for money
    public int getMoneyMulti(){
        return moneyMulti;
    }
}
