package player;

public enum Country {
    VAKANNDA(53,50, 62),
    MCD(15,250,74),
    VENESUELA(5,500,80);

    private int birthMortal;
    private int money;
    private int avgAge;

    Country (int birthMortal, int money, int avgAge){
        this.birthMortal=birthMortal;
        this.money=money;
        this.avgAge=avgAge;
    }
    public int getBirthMortal() {return birthMortal;}
    public int getMoney() {return money;}
    public int getAvgAge() {return avgAge;}
}
