package controller;

import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;

public class ResourceController {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int OVERLAYWIDTH = 1280;
    public static final int OVERLAYHEIGHT = 128;

    private static Scene homeScene;
    private static Scene outsideScene;
    private static Scene shopScene;
    private static Scene startmenuScene;
    private static Scene hospitalScene;
    private static Scene schoolScene;
    private static Scene workScene;

    private static SubScene overlayScene;
    private static OverlayController overlayController;

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

    public static SubScene getOverlayScene() {
        return overlayScene;
    }

    public static OverlayController getInventoryController() { return overlayController; }

    private static Scene loadScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        return new Scene(loader.load(), WIDTH , HEIGHT);
    }

    private static SubScene loadSubScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));

        SubScene subScene = new SubScene(loader.load(), OVERLAYWIDTH , OVERLAYHEIGHT);

        // This line of code explicitly works because the function is called only once
        // If the function is called with other SubScenes than overlay, it will produce an error
        overlayController = loader.getController();

        return subScene;
    }

    public static void loadMenu() throws Exception {
        startmenuScene = loadScene("/fxml/startmenu.fxml");

        // set initial scene to menu scene
        Run.getPrimaryStage().setScene(getStartmenuScene());
        Run.getPrimaryStage().show();
    }

    public static void loadRooms() throws Exception {
        homeScene = loadScene("/fxml/home.fxml");
        outsideScene = loadScene("/fxml/outside.fxml");
        hospitalScene = loadScene("/fxml/hospital.fxml");
        schoolScene = loadScene("/fxml/school.fxml");
        workScene = loadScene("/fxml/work.fxml");
        shopScene = loadScene("/fxml/shop.fxml");
        overlayScene = loadSubScene("/fxml/overlay.fxml");
    }
}
