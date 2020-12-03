package controller;

import gameEngine.Run;
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
    private static SubSceneData overlayData;

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

    public static SubSceneData getOverlayData() {
        return overlayData;
    }

    private static SceneData loadScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        SceneData data = new SceneData();
        data.scene = new Scene(loader.load(), WIDTH, HEIGHT);
        data.controller = loader.getController();
        return data;
    }

    private static SubSceneData loadSubScene(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(ResourceController.class.getResource(fileName));
        SubSceneData data = new SubSceneData();
        data.scene = new SubScene(loader.load(), OVERLAYWIDTH, OVERLAYHEIGHT);
        data.controller = loader.getController();
        return data;
    }

    public static void loadMenu() throws Exception {
        startmenuData = loadScene("/fxml/startmenu.fxml");

        // set initial scene to menu scene
        Run.getPrimaryStage().setScene(getStartmenuData().scene);
        Run.getPrimaryStage().show();
    }

    public static void loadRooms() throws Exception {
        homeData = loadScene("/fxml/home.fxml");
        outsideData = loadScene("/fxml/outside.fxml");
        hospitalData = loadScene("/fxml/hospital.fxml");
        schoolData = loadScene("/fxml/school.fxml");
        workData = loadScene("/fxml/work.fxml");
        shopData = loadScene("/fxml/shop.fxml");

        overlayData = loadSubScene("/fxml/overlay.fxml");
    }
}
