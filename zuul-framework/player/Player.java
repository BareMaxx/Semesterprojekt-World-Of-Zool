package player;

import gameplay.Sickness;
import gameplay.WorkDMG;
import item.Item;
import gameplay.Room;

import java.util.ArrayList;

// The player class contains all the variables
public class Player {
    private Country country;
    private Gender gender;
    private FamilyEconomy familyEconomy;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int money = 0;
    private int age = 1;
    private String stage;
    private boolean alive = true;
    private int knowledge = 0;
    private Room currentRoom;
    private Sickness sickness = null;
    private WorkDMG dmg = null;
    private int sickChance = 0;
    private int dmgChance = 0;
    private int avgAge;
    private String deathCause;

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country c){
        country = c;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender g){
        gender = g;
    }

    public FamilyEconomy getFamilyEconomy() {
        return familyEconomy;
    }
    public void setFamilyEconomy(FamilyEconomy f){
        familyEconomy = f;
    }

    public String getDeathCause() { return deathCause; }

    // Switch between different economy classes based on knowledgePoints
    public void moveFamilyEconomy() {
        switch (this.familyEconomy) {
            case POOR -> {
                if (this.knowledge >= 350) {
                    setFamilyEconomy(FamilyEconomy.MIDDLECLASS);
                }
            } case MIDDLECLASS -> {
                if (this.knowledge >= 900) {
                    setFamilyEconomy(FamilyEconomy.RICH);
                } else {
                    setFamilyEconomy(FamilyEconomy.POOR);
                }
            } case RICH -> {
                if (this.knowledge < 1050) {
                    setFamilyEconomy(FamilyEconomy.MIDDLECLASS);
                }
            }
        }
        System.out.println("You are now in the " + this.familyEconomy.toString().toLowerCase() + " class");
    }

    // Items
    public void addInventoryItem(Item s){
        inventory.add(s);
    }
    public void removeInventoryItem(Item i){
        inventory.remove(i);
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // Money
    public int getMoney(){
        return money;
    }
    public void incMoney(int i){
        money = money + i;
    }
    public void decMoney(int i){
        money = money - i;
    }

    public int getAge() {
        return age;
    }
    // Increment age and die if too old
    public void incAge(int i) {
        age = age + i;
        if (age >= avgAge) {
            kill("old age");
        }
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getAvgAge() {
        return avgAge;
    }
    public void setAvgAge(int avgAge) {
        this.avgAge = avgAge;
    }

    // Set and get the current stage, as well as move economy class
    public String getStage(){
        return stage;
    }
    public void setStage(String s){
        stage = s;
        if (s.equals("adult")) {
            moveFamilyEconomy();
        }
    }

    public boolean getAlive() {
        return alive;
    }
    // Kill the player with a cause
    public void kill(String cause) {
        alive = false;
        deathCause = cause;
    }

    // KnowledgePoints
    public void incKnowledge(int i){
        knowledge = knowledge +i;
    }
    public int getKnowledge() { return knowledge; }

    // Current room
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }

    // Set sickness
    public void setSickness(Sickness sickness) {
        this.sickness = sickness;
    }
    public Sickness getSickness() {
        return sickness;
    }

    // Set injury
    public void setDmg(WorkDMG dmg) {
        this.dmg = dmg;
    }
    public WorkDMG getDmg() {
        return dmg;
    }

    // Chance of getting sick
    public int getSickChance() {
        return sickChance;
    }
    public void incSickChance(int i) {
        sickChance = sickChance + i;
    }
    public void decSickChance(int i) {
        sickChance = sickChance - i;
    }

    // Chance of getting an injury
    public int getDmgChance() {
        return dmgChance;
    }
    public void incDmgChance(int i) {
        dmgChance = dmgChance + i;
    }
    public void decDmgChance(int i) {
        dmgChance = dmgChance - i;
    }
}