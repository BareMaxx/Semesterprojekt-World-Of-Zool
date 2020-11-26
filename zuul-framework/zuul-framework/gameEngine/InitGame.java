package gameEngine;

import commands.CommandWord;
import commands.Parser;
import gameplay.RandomEngine;
import item.Book;
import item.Protectors;
import player.Country;
import player.FamilyEconomy;
import player.Gender;
import player.Player;
import gameplay.Room;

public class InitGame {
    private Parser parser;
    private RandomEngine ran = new RandomEngine();

    InitGame(Player p1, Parser parser) {
        this.parser = parser;
        printWelcome(p1);
    }

    private void printWelcome(Player p1) {
        System.out.println();
        System.out.println("welcome to real life bitch");
        System.out.println("real life sucks");
        setCountry(p1);
        setGender(p1);
        setEcon(p1);
        setMoney(p1);
        createRooms(p1);
        System.out.println("You have been born as a " + p1.getFamilyEconomy().toString().toLowerCase() + " " +
                p1.getGender().toString().toLowerCase() + " living in " + p1.getCountry().toString().toLowerCase());
        p1.setStage("child");
        if (childDeath(p1))
            return;
        System.out.println("Your start with " + p1.getMoney() + " gold.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(p1.getCurrentRoom().getLongDescription());
    }

    private void createRooms(Player p1) {
        Room home, work, shop, school, hospital, outside;

        outside = new Room("outside", "outside", false);
        home = new Room("home", "at home", false);
        //work = new Room("work", "at work", true);
        work = new Room("work", "at work", false);
        shop = new Room("shop","in a shop", false);
        school = new Room("school", "at school", false);
        hospital = new Room("hospital","in a hospital", true);

        outside.setExit("home", home);
        outside.setExit("work", work);
        outside.setExit("shop", shop);
        outside.setExit("hospital", hospital);
        outside.setExit("school", school);

        home.setExit("outside", outside);
        work.setExit("outside", outside);
        shop.setExit("outside", outside);
        hospital.setExit("outside", outside);
        school.setExit("outside", outside);

        Book b1 = new Book("Algorithms", p1.getCountry().getMoney() * 7, 150);
        Book b2 = new Book("Math",p1.getCountry().getMoney() * 15,300);
        Book b3 = new Book("sql",p1.getCountry().getMoney() * 27,600);
        Protectors mask = new Protectors("mask", 50, 2, "sickness");
        Protectors helmet = new Protectors("helmet", 50, 2, "dmg");

        shop.setItem(b1);
        shop.setItem(b2);
        shop.setItem(b3);
        shop.setItem(mask);
        shop.setItem(helmet);

        p1.setCurrentRoom(home);
    }

    public boolean childDeath(Player p1){
        if(ran.getOutcome(p1.getCountry().getBirthMortal(), 1000)){
            System.out.println("Sadly the game is already over, you died at birth. Every year " +
                    p1.getCountry().getBirthMortal() + " out of 1000 infants die at birth in " + p1.getCountry().toString().toLowerCase());
            p1.setAlive(false);
            return true;
        }
        return false;
    }

    public void setCountry(Player p1) {
        System.out.println("Please select a country \n" +
                "Vakannda | WashingGeorge | Danheim");

        String s = parser.getWord().toUpperCase();
        boolean b = false;
        for (Country c : Country.values()) {
            if (c.toString().equals(s))
                b = true;
        }
        if (b) {
            p1.setCountry(Country.valueOf(s));
            p1.incSickChance(p1.getCountry().getEventChance());
            p1.incDmgChance(p1.getCountry().getEventChance());
        }
        else {
            System.out.println("Input invalid\n");
            setCountry(p1);
        }
    }

    public void setGender(Player p1) {
        p1.setGender(Gender.values()[ran.getRandom(0, 1)]);
    }

    public void setEcon(Player p1) {
        if(ran.getOutcome(p1.getCountry().getPoor(), 100))
            p1.setFamilyEconomy(FamilyEconomy.POOR);
        else if(ran.getOutcome(p1.getCountry().getMiddleClass(), 100))
            p1.setFamilyEconomy(FamilyEconomy.MIDDLECLASS);
        else
            p1.setFamilyEconomy(FamilyEconomy.RICH);
    }

    public void setMoney(Player p1) {
        p1.incMoney(p1.getCountry().getMoney() * p1.getGender().getMoneyMulti() * p1.getFamilyEconomy().getMoneyMulti());
    }
}