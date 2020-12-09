package controller;

import gameEngine.SceneData;
import gameEngine.SubSceneData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;

public class ResourceController {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int OVERLAYWIDTH = 1280;
    public static final int OVERLAYHEIGHT = 128;

    private static SceneData homeData;
    private static SceneData outsideData;
    private static SceneData shopData;
    private static SceneData startmenuData;
    private static SceneData hospitalData;
    private static SceneData schoolData;
    private static SceneData workData;
    private static SceneData deathData;
    private static SubSceneData overlayData;


    // Getters for every Scene and SubScene
    public static SceneData getHomeData() {
        return homeData;
    }

    public static SceneData getShopData() {
        return shopData;
    }

    public static SceneData getOutsideData() {
        return outsideData;
    }

    public static SceneData getStartmenuData() {
        return startmenuData;
    }

    public static SceneData getHospitalData() {
        return hospitalData;
    }

    public static SceneData getSchoolData() {
        return schoolData;
    }

    public static SceneData getWorkData() {
        return workData;
    }

    public static SceneData getDeathData() {
        return deathData;
    }

    public static SubSceneData getOverlayData() {
        return overlayData;
    }

    // Load the Scene and Controller from the given path
    private static SceneData loadScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        SceneData data = new SceneData();
        data.scene = new Scene(loader.load(), WIDTH, HEIGHT);
        data.controller = loader.getController();
        return data;
    }

    // Load the SubScene and Controller from the given path
    private static SubSceneData loadSubScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        SubSceneData data = new SubSceneData();
        data.scene = new SubScene(loader.load(), OVERLAYWIDTH, OVERLAYHEIGHT);
        data.controller = loader.getController();
        return data;
    }

    // Load the menu Scene and StartmenuController
    public static void loadMenu() throws Exception {
        startmenuData = loadScene("/fxml/startmenu.fxml");
    }

    // Load all the rooms after the game has been initialized
    public static void loadRooms() throws Exception {
        homeData = loadScene("/fxml/home.fxml");
        outsideData = loadScene("/fxml/outside.fxml");
        hospitalData = loadScene("/fxml/hospital.fxml");
        schoolData = loadScene("/fxml/school.fxml");
        workData = loadScene("/fxml/work.fxml");
        shopData = loadScene("/fxml/shop.fxml");

        deathData = loadScene("/fxml/death.fxml");

        overlayData = loadSubScene("/fxml/overlay.fxml");
    }
}
