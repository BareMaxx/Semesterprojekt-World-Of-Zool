package player;

import java.util.HashMap;

public enum Country {
    VAKANNDA(53,50, 1.00, 61,34,5, 6, new HashMap<>() {{
        put("old age", " In 2020 only 5% of the population in Africa made it beyond the age of 60," +
                " even though there are 1.2 billion people living on the continent." +
                " In comparison around 20% of the population in Denmark is above the age of 60.");
        put("cancer", " Over one-third of all cervical cancer deaths globally occur in sub-Saharan Africa," +
                " though the region represents only 14% of the world female population." +
                " Generally, cancer rates are highest in countries whose populations have the highest life expectancy, " +
                "education level, and standard of living. But for some cancer types, " +
                "such as cervical cancer, the reverse is true, and the incidence " +
                "rate is highest in countries in which the population ranks low on these measures." +
                " The chance to develop, diagnose and treat cancers, " +
                " are all highly related to the economic strength of your country." +
                " You are more likely to survive cancer if you are born in a richer country.");
        put("AIDS", " In 2019 around 690 000 people died from AIDS-related illnesses. " +
                " Even though Africa is only home to 15% of the worlds population, " +
                " they account for more than 66% of the global infection burden." +
                " AIDS is not considered terminal illness in Denmark, and the chances of contracting AIDS in Denmark" +
                " are astronomically low. ");
        put("malaria", " The WHO African Region carries a disproportionately high share of the global Malaria burden." +
                " In 2019, the region was home to 94% of malaria cases and deaths." +
                " Malaria is a problem that requires economic means to fix," +
                " and unfortunately most governments in Africa" +
                " do not have the economic means to do so. ");
        put("tuberculosis", "Even though Africa is home to only 11% of the worlds population," +
                " it carries 29% of the global burden of tuberculosis and 34% of the related deaths." +
                " Tuberculosis was considered eradicated from Denmark in the 1980's, though due to migration, " +
                " there is a small margin of people with tuberculosis in Denmark around 290 cases. ");
        put("birthMortal", "\"You died within the first months of your life. " +
                "Because of lack of access to amenities, like clean water and healthcare, " +
                "Sub-Saharan Africa accounts for 38% of global neonatal death, " +
                "and the amount of deaths per 1000 is around ten times higher than in Denmark\"");
    }}),
    WASHINGGEORGE(15,250,1.33, 29,52,19, 4, new HashMap<>() {{
        put("old age", "In 2020 Around 1.8% of the population in America made it to the age of 80, that's roughly" +
                "7 million people, and around 12% of the population made it to 60+. " +
                " your odds of living to 60 and 80, are markedly better in Denmark than in the US");
        put("a heart disease","Heart disease is the leading cause of death in the united states. " +
                " A person dies every 36 seconds from heart disease in the US");
        put("cancer","Around 22% of deaths in the US are from cancer, making it the second leading cause of death" +
                "after heart disease. Risk factors include obesity, smoking, and heavy drinking. " +
                "due to unhealthy living, around 37-39% of the population will have diagnosed cancer in their lifetime");
        put("AIDS","Around 0.3% of the population in America have AIDS, and around 1% of this 0.3% die if AIDS every year");
        put("diabetes","Around 10% of Americans have diabetes, diabetes is a major cause of blindness, kidney failure, " +
                " heart attacks, stroke, and lower limb ambutiation. Limited access to healthcare, affordable healthy food" +
                "diabetes is the seventh leading cause of death in the US");
        put("birthMortal","You died within the first months of your life. This can happen due to a lack of amenities" +
                "like access to good sanitation and healthcare. The child mortality rate in America is twice as high as in Denmark");
    }}),
    DANHEIM(5,500,1.35, 20,70,10, 2, new HashMap<>() {{
        put("old age", "In 2020 around 4.5% of the population in Denmark made it to the ripe old age of 80+ " +
                " what constitutes \"old age\" is related to the average life expectancy in a country." +
                " In comparison, the average life expectancy in Africa is around 15-20 years lower. " +
                " While at the same time only around 5% of the population made it past 60.");
        put("a heart disease", "Heart disease is second most common cause of death in Denmark, " +
                "and around 25% of all danes die from heart disease. ");
        put("cancer", "Cancer is the most common cause of death in Denmark, and around a third of the population " +
                "develop cancer before they turn 75, and around a third of that third survive. ");
        put("depression and you want to commit suicide", "Suicide constitutes 1.1% of yearly deaths or approximately" +
                " two suicides pr day");
        put("birthMortal"," You have died within the first months of your life. Even with access to healthcare, and " +
                "similar amenities. There is still a chance that you don't make it.");
    }});

    private int birthMortal;
    private int money;
    private double avgAgeMultiplier;
    private int poor;
    private int middleClass;
    private int rich;
    private int eventChance;
    private HashMap<String, String> stats;

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

    public String getStats(String cause) {
        return stats.get(cause);
    }
}