package player;

import gameplay.Sickness;
import gameplay.WorkDMG;
import item.Item;
import gameplay.Room;

import java.util.ArrayList;

public class Player {
    private Country country;
    private Gender gender;
    private FamilyEconomy familyEconomy;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int money = 0;
    private int age = 1;
    private String occupation;
    private String stage;
    private boolean alive = true;
    private int score = 0;
    private int knowledge = 0;
    private Room currentRoom;
    private Sickness sickness = null;
    private WorkDMG dmg = null;
    private int sickChance = 0;
    private int dmgChance = 0;
    private int avgAge;

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

    public void addInventoryItem(Item s){
        inventory.add(s);
    }
    public void removeInventoryItem(Item i){
        inventory.remove(i);
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void inventoryPrinter(){
        System.out.println("In your inventory you find:");
        if(inventory.isEmpty()) {
            System.out.println("\tnothing");
        }
        else {
            for (Item i : inventory) {
                System.out.println("\t" + i.getName());
            }
        }
    }

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
    public void incAge(int i) {
        age = age + i;
        if (age >= avgAge) {
            setAlive(false);
        }
    }

    public int getAvgAge() {
        return avgAge;
    }
    public void setAvgAge(int avgAge) {
        this.avgAge = avgAge;
    }

    public int getScore() {
        return score;
    }
    public void incScore(int i){
        score = score + i;
    }

    public String getOccupation(){
        return occupation;
    }
    public void setOccupation(String s){
        occupation = s;
    }

    public String getStage(){
        return stage;
    }
    public void setStage(String s){
        stage = s;
        if(s.equals("adult")) {
            moveFamilyEconomy();
        }
    }

    public boolean getAlive() {
        return alive;
    }
    public void setAlive(boolean b) {
        alive = b;
    }

    public void incKnowledge(int i){
        knowledge = knowledge +i;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }

    public void setSickness(Sickness sickness) {
        this.sickness = sickness;
    }
    public Sickness getSickness() {
        return sickness;
    }

    public void setDmg(WorkDMG dmg) {
        this.dmg = dmg;
    }
    public WorkDMG getDmg() {
        return dmg;
    }

    public int getSickChance() {
        return sickChance;
    }
    public void incSickChance(int i) {
        sickChance = sickChance + i;
    }
    public void decSickChance(int i) {
        sickChance = sickChance - i;
    }

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