package com.example.swapper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Singleton;

import java.io.IOException;



public class LandingPageController {
    @FXML
    private Button login;
    @FXML
    private Button addproduct;


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        Singleton.getInstance().setNameOfProduct(Item.getId());
        HelloApplication main = new HelloApplication();
        main.changeScene("views/categoryPage.fxml");

    }


    @FXML
    public void initialize(){
        boolean status = true;
        if(status == true){
            login.setVisible(true);
        }
        else {
            addproduct.setVisible(true);
        }
    }
}
