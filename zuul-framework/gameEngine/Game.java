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
        this.turns = new Turns(turns, this);
    }

    // Processes commands. Derived classes have their own special overrides
    public void processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();

        switch(commandWord) {
            case GO -> goRoom(command);
            case QUIT -> quit(command);
            case USE -> {use(command); turns.decTurns(1);}
            case BUY -> {buy(command);}
            case SLEEP -> sleep();
            case HEAL -> heal();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            default -> System.out.println("You can't do that at the current stage");
        }
        checkTurns();
    }

    // Decrement the amount of turns before death by sickness
    public void decrementSickTurns(int amount) {
        if (player.getSickness() != null) {
            player.getSickness().decTurnLimit(amount);
            if (player.getSickness().getTurnLimit() <= 0) {
                player.kill(player.getSickness().getName());
            }
        }
    }

    // Heal any sickness or injury, when in the hospital
    private void heal() {
        if (!inRoom(HOSPITAL_NAME)) {
            return;
        }
        if (player.getSickness() != null) { // Check for sickness
            if (player.getMoney() >= player.getSickness().getPrice()) {
                player.decMoney(player.getSickness().getPrice());
                player.setSickness(null);
                ((OverlayController) ResourceController.getOverlayData().controller).hideSickTurns(); // Update GUI
                System.out.println("You have been healed");
                player.getCurrentRoom().lock();
            } else {
                System.out.println("You don't have enough money to do that.");
            }
        } else if (player.getDmg() != null) { // Check for injuries
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

    // Check if player is in the given room
    public boolean inRoom(String room) {
        if (!player.getCurrentRoom().getName().equals(room)) {
            System.out.println("You have to be at " + room + " to do that");
            return false;
        }
        return true;
    }

    // Earn money according to econStage, gender and sickness/injuries
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
        turns.decTurns(6);
        randomEvent(2);
        checkTurns();
    }

    // Sleep and advance to the next stage
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
                player.kill("old age");
            }
        }

        // Update stage textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).increaseStage();

        // Update turns until.. textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateTurnsUntilChangeText();
    }

    // Use the given item
    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println();
            System.out.println("Use what?");
        } else {
            String item = command.getSecondWord();

            for (Item i: player.getInventory()) {
                if (i.getName().equals(item)) {
                    i.use(player, turns);
                    return;
                }
            }
            System.out.println();
            System.out.println("You have no item of that name.");
        }

        if (player.getInventory().isEmpty()) {
            System.out.println();
            System.out.println("You have no items to use.");
        }
    }

    // Buy the given item if you're in the "Shop" room
    private void buy(Command command) {
        if (player.getCurrentRoom().getName().equals(SHOP_NAME)) {

            String s = command.getSecondWord();

            PurchasableItem i = player.getCurrentRoom().getItem(s);
            if (i != null) {
                if (player.getMoney() >= i.getPrice()) {
                    player.addInventoryItem(i);
                    player.getCurrentRoom().removeItem(i);
                    player.decMoney(i.getPrice());
                    turns.decTurns(1);

                    System.out.println();
                    System.out.println("You bought " + s);
                    randomSickEvent(player.getSickChance() * 2);

                } else {
                    System.out.println();
                    System.out.println("You don't have enough money for this!");
                }
            }
        }
    }

    // Go to the given room
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println();
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            //System.out.println("There is no door!");
        } else if (nextRoom.isLocked()) {
            System.out.println();
            System.out.println("This door is locked.");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println();
            System.out.println(player.getCurrentRoom().getLongDescription());
            turns.decTurns(1);
            randomEvent(1);
        }
    }

    // Trigger a random event
    private void randomEvent(int multi) {
        RandomEngine r = new RandomEngine();
        int i = r.getRandom(0,1);
        switch(i) {
            case 0 -> randomSickEvent(player.getSickChance() * multi);
            case 1 -> randomDmgEvent(player.getDmgChance() * multi);
        }
    }

    // Random sickness
    private void randomSickEvent(int probability) {
        Sickness s = new Sickness(probability, player);
        if (s.getName() != null) {
            player.setSickness(s);
        }
    }

    // Random injury
    private void randomDmgEvent(int probability) {
        WorkDMG dmg = new WorkDMG(probability, player);
        if (dmg.getName() != null) {
            player.setDmg(dmg);
        }
    }

    private void quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println();
            System.out.println("Quit what?");
        }
        else {
            player.kill("ragequit");
        }
    }

    public Player getPlayer() {
        return player;
    }

    // I have no clue
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
                player.setAge(21);

                // Update stage textfield in overlay
                ((OverlayController) ResourceController.getOverlayData().controller).increaseStage();
                // Update turns until.. textfield in overlay
                ((OverlayController) ResourceController.getOverlayData().controller).updateTurnsUntilChangeText();

            // If the player is adult, commit self deletus
            } else {

                // But only if the adult player is older than 21
                if (player.getAge() > 21){
                    player.setAge(player.getAvgAge());
                    player.kill("old age");
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
