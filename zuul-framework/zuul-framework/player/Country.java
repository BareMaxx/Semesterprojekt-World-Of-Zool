package player;

public enum Country {
    VAKANNDA(53,50, 62, 61,34,5, 10),
    WASHINGGEORGE(15,250,74, 29,52,19, 8),
    DANHEIM(5,500,80, 20,70,10, 5);

    private int birthMortal;
    private int money;
    private int avgAge;
    private int poor;
    private int middleClass;
    private int rich;
    private int eventChance;

    //Country (int birthMortal, int money, int avgAge, int[] familyEcon){
    Country (int birthMortal, int money, int avgAge, int poor, int middleClass, int rich, int eventChance){
        this.birthMortal = birthMortal;
        this.money = money;
        this.avgAge = avgAge;
        this.poor = poor;
        this.middleClass = middleClass;
        this.rich = rich;
        this.eventChance = eventChance;
    }
    public int getBirthMortal() {
        return birthMortal;
    }
    public int getMoney() {
        return money;
    }
    public int getAvgAge() {
        return avgAge;
    }
    public int getPoor() {
        return poor;
    }
    public int getMiddleClass() {
        return middleClass;
    }
    public int getRich() {
        return rich;
    }
    public int getEventChance(){
        return eventChance;
    }
}
