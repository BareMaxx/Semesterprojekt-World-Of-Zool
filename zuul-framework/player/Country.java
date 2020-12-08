package player;

import java.util.HashMap;

public enum Country {
    VAKANNDA(53,50, 1.00, 61,34,5, 6, new HashMap<>() {{
        put("old age", "yoink");
        put("cancer", "yoink");
        put("AIDS", "yoink");
        put("malaria", "yoink");
    }}),
    WASHINGGEORGE(15,250,1.33, 29,52,19, 4, new HashMap<>() {{
        put("old age", "yoink");
    }}),
    DANHEIM(5,500,1.35, 20,70,10, 2, new HashMap<>() {{
        put("old age", "yoink");
    }});

    private int birthMortal;
    private int money;
    private double avgAgeMultiplier;
    private int poor;
    private int middleClass;
    private int rich;
    private int eventChance;
    private HashMap<String, String> stats;

    Country (int birthMortal, int money, double avgAgeMultiplier, int poor, int middleClass, int rich, int eventChance, HashMap<String, String> stats) {
        this.birthMortal = birthMortal;
        this.money = money;
        this.avgAgeMultiplier = avgAgeMultiplier;
        this.poor = poor;
        this.middleClass = middleClass;
        this.rich = rich;
        this.eventChance = eventChance;
        this.stats = stats;
    }

    public int getBirthMortal() {
        return birthMortal;
    }
    public int getMoney() {
        return money;
    }
    public double getAvgAgeMultiplier() {
        return avgAgeMultiplier;
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

    public String getStats(String cause) {
        return stats.get(cause);
    }
}