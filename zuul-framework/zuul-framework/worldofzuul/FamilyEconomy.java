package worldofzuul;

public enum FamilyEconomy {
    RICH(10),
    MIDDLECLASS(7),
    POOR(5);

    private int moneyMulti;

    FamilyEconomy(int moneyMulti){
        this.moneyMulti=moneyMulti;
    }
    public int getMoneyMulti(){return moneyMulti;}
}
