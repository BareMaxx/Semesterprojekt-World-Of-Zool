package gameEngine;

import commands.Command;
import commands.Parser;
import controller.DeathController;
import controller.OverlayController;
import controller.ResourceController;
import controller.ShopController;
import gameplay.Room;
import javafx.application.Application;
import javafx.stage.Stage;
import player.Player;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Run extends Application {
    private Parser parser;
    private Player player;
    private Child c;
    private Adult a;
    private ByteArrayOutputStream stream;

    private static Room shopRoom;
    private static Run rInstance;
    private static Stage primaryStage;

    public Run() {
        rInstance = this;
        parser = new Parser();
        player = new Player();
        c = new Child(player);
        a = new Adult(player);

        stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
    }

    // Get the primary "Stage"
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // Get the current instance of Run
    public static Run getRInstance() {
        return rInstance;
    }

    // Get a reference to the shop room
    public static Room getShopRoom() {
        return shopRoom;
    }

    // Create all the JavaFX stuff, like the window
    public void launchMenu() {
        launch();
    }

    // Parse the given string as a command and update the game logic
    public void processCommand(String input) {
        Command command = parser.getCommand(input);

        switch (player.getStage()) {
            case "child" -> c.processCommand(command);
            case "adult" -> a.processCommand(command);
        }

        // Update event log from console
        String console = stream.toString();
        ((OverlayController)ResourceController.getOverlayData().controller).updateEventLog(console);

        // Update inventory
        ((OverlayController)ResourceController.getOverlayData().controller).updateInventory();

        // Update Shop stock
        if (player.getCurrentRoom().getName().equals("shop")) {
            ((ShopController)ResourceController.getShopData().controller).updateStock();
        }

        // Update death screen
        if (!player.getAlive()) {
            getPrimaryStage().setScene(ResourceController.getDeathData().scene);
            ((DeathController) ResourceController.getDeathData().controller).updateDeathScreen();
        }

        // Update money textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateMoney();

        // Update turn textfield in overlay
        if (player.getStage().equals("child")) {
            ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(c.turns.getTurns());
        } else {
            ((OverlayController) ResourceController.getOverlayData().controller).updateTurns(a.turns.getTurns());
        }

        // Update age textfield in overlay
        ((OverlayController) ResourceController.getOverlayData().controller).updateAge();
    }

    // Initialize the map
    public void initGame(String country) {
        InitGame init = new InitGame(player, country);
        shopRoom = init.createRooms(player);
        init.printWelcome(player);
    }

    // This function is called when an instance of the Run class is created.
    // This is due to the fact that Run extends the JavaFX class Application.
    // See JavaFX documentation for further explanation...
    @Override
    public void start(Stage primaryStage) throws Exception {
        Run.primaryStage = primaryStage;

        // ResourceController is entirely static, therefore it is not instantiated
        // set initial scene to menu scene
        ResourceController.loadGuide();

        // set initial scene to menu scene
        primaryStage.setTitle("ZUUUUL");
        primaryStage.setScene(ResourceController.getGuideData().scene);
        primaryStage.show();
    }

    // Get a reference to the player
    public Player getPlayer() {
        return player;
    }
}