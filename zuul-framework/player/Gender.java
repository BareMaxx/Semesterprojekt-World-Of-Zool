package player;

public enum Gender {
    MALE(10,1),
    FEMALE(7,1.05);

    private int moneyMulti;
    private double avgAgeMulti;

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
