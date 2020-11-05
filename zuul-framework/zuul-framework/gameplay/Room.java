package gameplay;

import item.Item;
import item.PurchasableItem;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room {
    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<PurchasableItem> stock;
    private ArrayList<Item> items;

    public Room(String name, String description) {
        this.setName(name);
        this.description = description;
        exits = new HashMap<String, Room>();
        stock = new ArrayList<PurchasableItem>();
    }

    public void setItem(PurchasableItem i) {
        stock.add(i);
    }

    public PurchasableItem getItem(String name){
        for (PurchasableItem i : stock) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        System.out.println("Item not in shop");
        return null;
    }
    public void removeItem(PurchasableItem i) {
        stock.remove(i);
    }
    public void printStock(){
        System.out.println("These objects are for sale:");
        if(stock.isEmpty())
            System.out.println("\tnothing");
        else{
            for(PurchasableItem i : stock){
                System.out.println("\t" + i.getName() + "\t|\t" + i.getPrice() + " gold");
            }
        }
    }
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
