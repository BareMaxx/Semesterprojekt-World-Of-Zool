package gameEngine;

import commands.CommandWord;
import gameplay.RandomEngine;
import item.Book;
import item.Protectors;
import player.Country;
import player.FamilyEconomy;
import player.Gender;
import player.Player;
import gameplay.Room;

public class InitGame {
    private RandomEngine ran = new RandomEngine();

    public InitGame(Player p1, String country) {
        setupPlayer(p1, country);
    }

    private void setupPlayer(Player player, String country) {
        setCountry(player, country);
        setGender(player);
        setEcon(player);
        setMoney(player);
    }

    public void printWelcome(Player player) {
        System.out.println();
        System.out.println("welcome to real life bitch");
        System.out.println("real life sucks");

        System.out.println("You have been born as a " + player.getFamilyEconomy().toString().toLowerCase() + " " +
                player.getGender().toString().toLowerCase() + " living in " + player.getCountry().toString().toLowerCase());
        player.setStage("child");
        if (childDeath(player))
            return;

        System.out.println("Your start with " + player.getMoney() + " gold.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public Room createRooms(Player player) {
        Room home, work, shop, school, hospital, outside;

        outside = new Room("outside", "outside", false);
        home = new Room("home", "at home", false);
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

        Book b1 = new Book("Algorithms", player.getCountry().getMoney() * 7, 150);
        Book b2 = new Book("Math",player.getCountry().getMoney() * 15, 300);
        Book b3 = new Book("sql",player.getCountry().getMoney() * 27, 600);
        Protectors mask = new Protectors("mask", 50, 2, "sickness");
        Protectors helmet = new Protectors("helmet", 50, 2, "dmg");

        shop.setItem(b1);
        shop.setItem(b2);
        shop.setItem(b3);
        shop.setItem(mask);
        shop.setItem(helmet);

        player.setCurrentRoom(home);

        return shop;
    }

    public boolean childDeath(Player player){
        if(ran.getOutcome(player.getCountry().getBirthMortal(), 1000)){
            System.out.println("Sadly the game is already over, you died at birth. Every year " +
                    player.getCountry().getBirthMortal() + " out of 1000 infants die at birth in " + player.getCountry().toString().toLowerCase());
            player.setAlive(false);
            return true;
        }
        return false;
    }

    public void setCountry(Player player, String country) {
        boolean b = false;
        country = country.toUpperCase();
        for (Country c : Country.values()) {
            if (c.toString().equals(country))
                b = true;
        }

        if (b) {
            player.setCountry(Country.valueOf(country));
            player.incSickChance(player.getCountry().getEventChance());
            player.incDmgChance(player.getCountry().getEventChance());
        }
        else {
            System.out.println("Input invalid\n");
            //setCountry(p1, country); // Endless loop
        }
    }

    public void setGender(Player player) {
        player.setGender(Gender.values()[ran.getRandom(0, 1)]);
    }

    public void setEcon(Player p1) {
        if (ran.getOutcome(p1.getCountry().getPoor(), 100))
            p1.setFamilyEconomy(FamilyEconomy.POOR);
        else if (ran.getOutcome(p1.getCountry().getMiddleClass(), 100))
            p1.setFamilyEconomy(FamilyEconomy.MIDDLECLASS);
        else
            p1.setFamilyEconomy(FamilyEconomy.RICH);
    }

    public void setMoney(Player player) {
        player.incMoney(player.getCountry().getMoney() * player.getGender().getMoneyMulti() * player.getFamilyEconomy().getMoneyMulti());
    }

    public void setAvgAge(Player player) {
        //58 is the standard age, which is the lowest age possible, which is the male average age in Vakannda (Uganda)
        player.setAvgAge((int)(58 * player.getCountry().getAvgAgeMultiplier() * player.getGender().getAvgAgeMulti()));
    }
}