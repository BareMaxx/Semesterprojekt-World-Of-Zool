package player;

// Enumeration that applies the differences of the genders you can be in the game.
public enum Gender {
    MALE(10,1),
    FEMALE(7,1.05);

    private int moneyMulti;
    private double avgAgeMulti;

    // Initialize gender with average age and money-multiplier
    Gender(int moneyMulti, double avgAgeMulti){
        this.moneyMulti = moneyMulti;
        this.avgAgeMulti = avgAgeMulti;
    }

    public int getMoneyMulti(){
        return moneyMulti;
    }
    public double getAvgAgeMulti(){
        return avgAgeMulti;
    }
}
