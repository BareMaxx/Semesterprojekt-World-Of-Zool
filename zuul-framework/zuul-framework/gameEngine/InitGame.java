package gameEngine;

import commands.CommandWord;
import commands.Parser;
import gameplay.RandomEngine;
import item.Book;
import player.Country;
import player.FamilyEconomy;
import player.Gender;
import player.Player;
import gameplay.Room;

public class InitGame {
    private Parser parser;
    private RandomEngine ran = new RandomEngine();

    InitGame(Player p1, Parser parser) {
        createRooms(p1);
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
        //todo death at birth event
        System.out.println("You have been born as " + p1.getFamilyEconomy().toString().toLowerCase() + " " +
                p1.getGender().toString().toLowerCase() + " living in " + p1.getCountry().toString().toLowerCase());
        p1.setStage("child");
        System.out.println("Your start with " + p1.getMoney() + " gold.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(p1.getCurrentRoom().getLongDescription());
    }

    private void createRooms(Player p1) {
        Room home, work, shop, school, hospital, outside;

        outside = new Room("Outside", "outside");
        home = new Room("Home", "at home");
        work = new Room("Work", "at work");
        shop = new Room("Shop","in a shop");
        school = new Room("School", "at school");
        hospital = new Room("Hospital","in a hospital");

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

        Book b1 = new Book("Algorithms",10,100);
        Book b2 = new Book("Math",20,200);
        Book b3 = new Book("sql",30,300);

        shop.setItem(b1);
        shop.setItem(b2);
        shop.setItem(b3);

        p1.setCurrentRoom(home);
    }

    public void setCountry(Player p1) {
        System.out.println("Please select a country \n" +
                "Vakannda | McD | Venesuela");

        String s = parser.getWord().toUpperCase();
        boolean b = false;
        for (Country c : Country.values()) {
            if (c.toString().equals(s))
                b = true;
        }
        if (b)
            p1.setCountry(Country.valueOf(s));
        else {
            System.out.println("Input invalid\n");
            setCountry(p1);
        }
    }

    public void setGender(Player p1) {
        p1.setGender(Gender.values()[ran.getRandom(0, 1)]);
    }

    public void setEcon(Player p1) {
        p1.setFamilyEconomy(FamilyEconomy.values()[ran.getRandom(0, 2)]);
    }

    public void setMoney(Player p1) {
        p1.incMoney(p1.getCountry().getMoney() * p1.getGender().getMoneyMulti() * p1.getFamilyEconomy().getMoneyMulti());
    }
}