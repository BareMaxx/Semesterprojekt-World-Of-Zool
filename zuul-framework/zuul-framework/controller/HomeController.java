package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController extends GenericController{

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView doorImage;

    @FXML
    private ImageView bedImage;


    public HomeController() throws  Exception{
        super();
    }

    @FXML
    public void initialize() {


        Image image = new Image("/png/ugHome.png");
        backgroundImage.setImage(image);
    }
}
