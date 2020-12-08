package gameEngine;

import commands.Command;
import commands.CommandWord;
import controller.OverlayController;
import controller.ResourceController;
import gameplay.Room;
import gameplay.Sickness;
import gameplay.Turns;
import gameplay.WorkDMG;
import gameplay.*;
import item.*;
import player.Player;

public class Game {
    protected final String SHOP_NAME = "shop";
    protected final String HOME_NAME = "home";
    protected final String HOSPITAL_NAME = "hospital";
    protected final String WORK_NAME = "work";

    private Player player;
    protected Turns turns;

    // Super constructor. Amount of turns decided by derived class
    public Game(Player player, int turns) {
        this.player = player;
        this.turns = new Turns(turns);
    }
    // Processes commands. Derived classes have their own special overrides
    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        endTurn();

        switch(commandWord) {
            case GO -> goRoom(command);
            case QUIT -> quit(command);
            case USE -> {use(command); turns.decTurns();}
            case BUY -> {buy(command);}
            case SLEEP -> sleep();
            case HEAL -> heal();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            default -> System.out.println("You can't do that at the current stage");
        }
        checkTurns();
    }

    private void endTurn() {
        if (player.getSickness()!=null) {
            player.getSickness().decTurnLimit(1);
            if (player.getSickness().getTurnLimit() == 0) {
                player.setAlive(false);
            }
        }
    }

    private void heal() {
        if (!inRoom(HOSPITAL_NAME)) {
            return;
        }
        if (player.getSickness() != null) {
            if (player.getMoney() >= player.getSickness().getPrice()) {
                player.decMoney(player.getSickness().getPrice());
                player.setSickness(null);
                ((OverlayController) ResourceController.getOverlayData().controller).hideSickTurns();
                System.out.println("You have been healed");
                player.getCurrentRoom().lock();
            } else {
                System.out.println("You don't have enough money to do that.");
            }
        } else if (player.getDmg() != null) {
            if (player.getMoney() >= player.getDmg().getPrice()) {
                player.decMoney(player.getDmg().getPrice());
                player.setDmg(null);
                System.out.println("You have been healed");
                player.getCurrentRoom().lock();
            } else {
                System.out.println("You don't have enough money to do that.");
            }
        } else {
            System.out.println("There is nothing to do here. You are healthy. Leave!");
        }
    }

    public boolean inRoom(String room) {
        if (!player.getCurrentRoom().getName().equals(room)) {
            System.out.println("You have to be at " + room + " to do that");
            return false;
        }
        return true;
    }

    public void work(int econStage) {
        if (!inRoom(WORK_NAME)) {
            return;
        }
        int i = player.getCountry().getMoney() * player.getGender().getMoneyMulti() *
                player.getFamilyEconomy().getMoneyMulti() / econStage;

        if (player.getSickness() != null && player.getDmg() != null) {
            i = i - 60 *  player.getFamilyEconomy().getMoneyMulti();
        } else if (player.getSickness() != null) {
            i = i - 40 *  player.getFamilyEconomy().getMoneyMulti();
        } else if (player.getDmg() != null) {
            i = i - 20 *  player.getFamilyEconomy().getMoneyMulti();
        }

        player.incMoney(i);
        System.out.println("You made " + i);
        randomEvent(2);
        turns.decTurns(6);
        checkTurns();
    }
    
    private void sleep() {
        if (!inRoom(HOME_NAME)) {
            return;
        }
        turns.decTurns(turns.getTurns());
        switch (player.getStage()) {
            case "child" -> {
                player.setStage("adult");
                System.out.println("You are now an adult");
            }
            case "adult" -> {
                player.setAlive(false);
            }
        }

        // Update stage textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).increaseStage();

        // Update turns until.. textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateTurnsUntilChangeText();
    }

    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
        } else {
            String item = command.getSecondWord();

            for (Item i: player.getInventory()) {
                if (i.getName().equals(item)) {
                    i.use(player, turns);
                    return;
                }
            }
            System.out.println("You have no item of that name.");
        }

        if (player.getInventory().isEmpty()) {
            System.out.println("You have no items to use.");
        }
    }

    // Buy an item if you're in the "Shop" room
    private void buy(Command command) {
        if (player.getCurrentRoom().getName().equals(SHOP_NAME)) {
            if (!command.hasSecondWord()) {
                System.out.println("Buy what?");
                return;
            }

            String s = command.getSecondWord();

            PurchasableItem i = player.getCurrentRoom().getItem(s);
            if (i != null) {
                if (player.getMoney() >= i.getPrice()) {
                    player.addInventoryItem(i);
                    player.getCurrentRoom().removeItem(i);
                    player.decMoney(i.getPrice());
                    turns.decTurns();

                    System.out.println("You bought " + s);
                    randomSickEvent(player.getSickChance() * 2);

                } else {
                    System.out.println("You don't have enough money for this!");
                }
            } else {
                System.out.println("There is no " + s + " in the shop");
            }
        }
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.isLocked()) {
            System.out.println("This door is locked.");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            turns.decTurns();
            randomEvent(1);
        }
    }

    private void randomEvent(int multi){
        RandomEngine r = new RandomEngine();
        int i = r.getRandom(0,1);
        switch (i){
            case 0 -> randomSickEvent(player.getSickChance() * multi);
            case 1 -> randomDmgEvent(player.getDmgChance() * multi);
        }
    }

    private void randomSickEvent(int probability){
        Sickness s = new Sickness(probability, player);
        if(s.name != null) {
            player.setSickness(s);
        }
    }
    private void randomDmgEvent(int probability) {
        WorkDMG dmg = new WorkDMG(probability, player);
        if (dmg.name != null) {
            player.setDmg(dmg);
        }
    }

    private void quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
        }
        else {
            player.setAlive(false);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void checkTurns() {

        /*
            Note:

            counter -   increaes whenever turns is used
                        (increases according to the amount of turns used)

            turns -     number of turns until adult stage or death
                        (number of turns is decreased with a arbitrary number when doing any action)
         */


        // When counter is a multiple of 3 or more
        if (turns.getCounter() / 3 > 0) {

            /*
                The player ages with 1 year per multiple of three of counter.
                When the player makes a move, the counter is increased with the number of turns used

                This means that:
                60 turns => 21 years, when getting 1 year older every three turns
                (when starting with the age of 1)
             */

            // ageMultiplier is 1 per multiple of three
            int ageMultiplier = turns.getCounter() / 3;
            // increase age with agemultiplier
            player.incAge(ageMultiplier);

            // Counter is reset when the player's age is increased
            turns.setCounter(0);
        }

        // If player has 0 turns left
        if (turns.getTurns() <= 0){

            // If player is a child
            if (player.getStage().equals("child")){

                // Then set stage to adult
                player.setStage("adult");

                // Update stage textfield in overlay
                ((OverlayController) ResourceController.getOverlayData().controller).increaseStage();
                // Update turns until.. textfield in overlay
                ((OverlayController) ResourceController.getOverlayData().controller).updateTurnsUntilChangeText();

            // If the player is adult, commit self deletus
            } else {

                // But only if the adult player is older than 21
                if (player.getAge() > 21){
                    player.setAlive(false);
                }

                /*
                    Note:
                    When sleeping, the player moves from child to adult stage.
                    This resets the counter and the number of truns. Therefore
                    the program concludes the player is dead when sleeping in
                    child stage. To fix this, I added an age check which prevents
                    the player from dying when sleeping as a child.
                 */
            }
        }
    }
}
