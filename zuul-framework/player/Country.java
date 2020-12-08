package player;

import java.util.HashMap;

public enum Country {
    VAKANNDA(53,50, 1.00, 61,34,5, 6, new HashMap<>() {{
        put("old age", "Only 5% of the population in Africa get above the age of 60," +
                " even though there are 1.2 billion people living on the continent." +
                " In comparison around 20% of the population in Denmark is above the age of 60.");
        put("cancer", "Over one-third of all cervical cancer deaths globally occur in sub-Saharan Africa," +
                " though the region represents only 14% of the world female population.");
        put("AIDS", "In 2019 around 690 000 people died from AIDS-related illnesses. " +
                "Even though Africa is only home to 15% of the worlds population, they account for more than 66% of the global infection burden.");
        put("malaria", "The WHO African Region carries a disproportionately high share of the global malaria burden." +
                " In 2019, the region was home to 94% of malaria cases and deaths.");
        put("tuberculosis", "Even though Africa is home to only 11% of the worlds population," +
                " it carries 29% of the global burden of tuberculosis and 34% of the related deaths.");
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

    // Initialize country with all it's attributes
    Country(int birthMortal, int money, double avgAgeMultiplier, int poor, int middleClass, int rich, int eventChance, HashMap<String, String> stats) {
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

    // Get the statistics
    public String getStats(String cause) {
        return stats.get(cause);
    }
}