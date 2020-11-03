
package worldofzuul;

import java.util.ArrayList;

public class Player {
    private Country country;
    private Gender gender;
    private FamilyEconomy familyEconomy;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int money = 0;
    private int age = 0;
    private String occupation;
    private String stage;
    private boolean alive = true;
    private int score = 0;
    private Room currentRoom;

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

    public void addInventoryItem(Item s){
        inventory.add(s);
    }

    public void removeInventoryItem(Item i){inventory.remove(i);}
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
                System.out.println("\t" + i.name);
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
    public void incAge(int i){
        age = age + i;
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
    }

    public boolean getAlive() {
        return alive;
    }
    public void setAlive(boolean b) {
        alive = b;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }
}