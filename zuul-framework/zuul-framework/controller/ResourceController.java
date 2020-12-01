package controller;

import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ResourceController {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private static Scene homeScene;
    private static Scene outsideScene;
    private static Scene shopScene;
    private static Scene startmenuScene;
    private static ResourceController resourceControllerInstance;

    public ResourceController(){
        resourceControllerInstance = this;
    }

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

    public static ResourceController getResourceControllerInstance() {
        return resourceControllerInstance;
    }

    private Scene loadRoom(String fileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        return new Scene(loader.load(), WIDTH , HEIGHT);
    }

    public void loadMenu() throws Exception {
        String fxmlFileStartmenu = ("/fxml/startmenu.fxml");
        FXMLLoader startmenuRoot = new FXMLLoader(getClass().getResource(fxmlFileStartmenu));
        StartmenuController startmenuController = new StartmenuController();
        startmenuRoot.setController(startmenuController);
        startmenuScene = new Scene(startmenuRoot.load(), WIDTH , HEIGHT);

        // set initial scene to menu scene
        Run.getPrimaryStage().setScene(getStartmenuScene());
        Run.getPrimaryStage().show();
    }

    public void loadRooms() throws Exception {

        String fxmlFileHome = ("/fxml/home.fxml");
        FXMLLoader homeRoot = new FXMLLoader(getClass().getResource(fxmlFileHome));
        HomeController homeController = new HomeController();
        homeRoot.setController(homeController);
        homeScene = new Scene(homeRoot.load(), WIDTH , HEIGHT);

        String fxmlFileOutside = ("/fxml/outside.fxml");
        FXMLLoader outsideRoot = new FXMLLoader(getClass().getResource(fxmlFileOutside));
        OutsideController outsidecontroller = new OutsideController();
        outsideRoot.setController(outsidecontroller);
        outsideScene = new Scene(outsideRoot.load(), WIDTH , HEIGHT);

        shopScene = loadRoom("/fxml/shop.fxml");
    }
}
