package controller;

import gameEngine.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.util.*;

public class ResourceController {

    private static Scene homeScene;
    private static Scene outsideScene;
    private static Scene startmenuScene;
    private static Scene hospitalScene;
    private static Scene schoolScene;

    public static Scene getHomeScene() {
        return homeScene;
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

    public static void loadMenu() throws Exception{

        String fxmlFileStartmenu = ("/fxml/startmenu.fxml");
        FXMLLoader startmenuRoot = new FXMLLoader(ResourceController.class.getResource(fxmlFileStartmenu));
        StartmenuController startmenuController = new StartmenuController();
        startmenuRoot.setController(startmenuController);
        startmenuScene = new Scene(startmenuRoot.load(), 1280 , 720);
    }

    public static void loadRooms() throws Exception{

        String fxmlFileHome = ("/fxml/home.fxml");
        FXMLLoader homeRoot = new FXMLLoader(ResourceController.class.getResource(fxmlFileHome));
        HomeController homeController = new HomeController();
        homeRoot.setController(homeController);
        homeScene = new Scene(homeRoot.load(), 1280 , 720);

        String fxmlFileOutside = ("/fxml/outside.fxml");
        FXMLLoader outsideRoot = new FXMLLoader(ResourceController.class.getResource(fxmlFileOutside));
        OutsideController outsideController = new OutsideController();
        outsideRoot.setController(outsideController);
        outsideScene = new Scene(outsideRoot.load(), 1280 , 720);

        String fxmlFileHospital = ("/fxml/hospital.fxml");
        FXMLLoader hospitalRoot = new FXMLLoader(ResourceController.class.getResource(fxmlFileHospital));
        HospitalController hospitalController = new HospitalController();
        hospitalRoot.setController(hospitalController);
        hospitalScene = new Scene(hospitalRoot.load(), 1280 , 720);

        String fxmlFileSchool = ("/fxml/school.fxml");
        FXMLLoader schoolRoot = new FXMLLoader(ResourceController.class.getResource(fxmlFileSchool));
        SchoolController schoolController = new SchoolController();
        schoolRoot.setController(schoolController);
        schoolScene = new Scene(schoolRoot.load(), 1280 , 720);
    }
}
