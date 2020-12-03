package controller;

import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;

public class ResourceController {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private static Scene homeScene;
    private static Scene outsideScene;
    private static Scene shopScene;
    private static Scene startmenuScene;
    private static Scene hospitalScene;
    private static Scene schoolScene;
    private static Scene workScene;
    private static SubScene inventoryScene;
    private static InventoryController inventoryController;

    public static Scene getHomeScene() {
        return homeScene;
    }

    public static Scene getShopScene() {
        return shopScene;
    }

    public static Scene getOutsideScene() {
        return outsideScene;
    }

    public static Scene getStartmenuScene() {
        return startmenuScene;
    }

    public static Scene getHospitalScene() {
        return hospitalScene;
    }

    public static Scene getSchoolScene() {
        return schoolScene;
    }

    public static Scene getWorkScene() {
        return workScene;
    }

    public static SubScene getInventoryScene() {
        return inventoryScene;
    }

    public static InventoryController getInventoryController() { return inventoryController; }

    private static Scene loadRoom(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        return new Scene(loader.load(), WIDTH , HEIGHT);
    }

    public static void loadMenu() throws Exception {
        startmenuScene = loadRoom("/fxml/startmenu.fxml");

        // set initial scene to menu scene
        Run.getPrimaryStage().setScene(getStartmenuScene());
        Run.getPrimaryStage().show();
    }

    public static void loadRooms() throws Exception {
        homeScene = loadRoom("/fxml/home.fxml");
        outsideScene = loadRoom("/fxml/outside.fxml");
        hospitalScene = loadRoom("/fxml/hospital.fxml");
        schoolScene = loadRoom("/fxml/school.fxml");
        workScene = loadRoom("/fxml/work.fxml");
        shopScene = loadRoom("/fxml/shop.fxml");

        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource("/fxml/inventory.fxml"));
        inventoryScene = new SubScene(loader.load(), 1280, 128);
        inventoryController = loader.getController();
    }
}
