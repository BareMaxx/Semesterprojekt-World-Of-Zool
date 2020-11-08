package player;

public enum Gender {
    MALE(10,7),
    FEMALE(7,10);

    private int moneyMulti;
    private int avgAgeMulti;

    Gender(int moneyMulti, int avgAgeMulti){
        this.moneyMulti=moneyMulti;
        this.avgAgeMulti=avgAgeMulti;
    }
    public int getMoneyMulti(){
        return moneyMulti;
    }
    public int getAvgAgeMulti(){
        return avgAgeMulti;
    }
}
