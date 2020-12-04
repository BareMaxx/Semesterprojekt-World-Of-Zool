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

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Run getRInstance() {
        return rInstance;
    }

    public static Room getShopRoom() {
        return shopRoom;
    }

    public void launchMenu() {
        launch();
    }

    public void processCommand(String input) {
        Command command = parser.getCommand(input);

        switch (player.getStage()) {
            case "child" -> c.processCommand(command);
            case "adult" -> a.processCommand(command);
        }

        // Update event log from console
        String console = new String(stream.toByteArray());
        ((OverlayController)ResourceController.getOverlayData().controller).updateEventLog(console);

        // Update inventory
        ((OverlayController)ResourceController.getOverlayData().controller).updateInventory();

        // Update Shop stock
        ((ShopController)ResourceController.getShopData().controller).updateStock();

        // Update death screen
        if (!player.getAlive()) {
            getPrimaryStage().setScene(ResourceController.getDeathData().scene);
            ((DeathController) ResourceController.getDeathData().controller).updateDeathScreen("Dead", "Not big\nsurprise");
        }
    }

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
        ResourceController.loadMenu();

        // Set initial scene to menu scene
        primaryStage.setTitle("ZUUUUL");
        primaryStage.setScene(ResourceController.getStartmenuData().scene);
        primaryStage.show();
    }

    public Player getPlayer() {
        return player;
    }
}