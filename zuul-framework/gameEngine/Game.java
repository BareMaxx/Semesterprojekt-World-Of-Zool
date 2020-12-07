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

        switch(commandWord) {
            case GO -> goRoom(command);
            case QUIT -> quit(command);
            case USE -> {use(command); turns.decTurns();}
            case BUY -> {buy(command); turns.decTurns();}
            case SLEEP -> sleep();
            case HEAL -> heal();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            default -> System.out.println("You can't do that at the current stage");
        }
        checkTurns();
        endTurn();
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
            i = player.getCountry().getMoney() * player.getGender().getMoneyMulti() *
                    player.getFamilyEconomy().getMoneyMulti() / econStage;
            i = i - 60 *  player.getFamilyEconomy().getMoneyMulti();
        } else if (player.getSickness() != null) {
            i = player.getCountry().getMoney() * player.getGender().getMoneyMulti() *
                    player.getFamilyEconomy().getMoneyMulti() / econStage;
            i = i - 40 *  player.getFamilyEconomy().getMoneyMulti();
        } else if (player.getDmg() != null) {
            i = player.getCountry().getMoney() * player.getGender().getMoneyMulti() *
                    player.getFamilyEconomy().getMoneyMulti() / econStage;
            i = i - 20 *  player.getFamilyEconomy().getMoneyMulti();
        }

        player.incMoney(i);
        System.out.println("You made " + i);
        randomEvent(2);
        turns.decTurns(5);
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
        if (turns.getCounter() / 3 > 0) {
            //60 turns => 21 years, when getting 1 year older every three turns
            int ageMultiplier = turns.getCounter() / 3;
            player.incAge(ageMultiplier);

            if (turns.getTurns() != 0) {
                turns.setCounter();
            } else {
                turns.setCounter(0);
            }
        } else {
            if (player.getStage().equals("child") && turns.getTurns() <= 0) {
                player.setStage("adult");

                // Update stage textfield in overlay
                ((OverlayController) ResourceController.getOverlayData().controller).increaseStage();

            } else if (player.getStage().equals("adult") && turns.getTurns() <= 0) {
                player.setAlive(false);
            }
        }
        // Update age textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateAge();

        ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(turns.getTurns());
    }
}
