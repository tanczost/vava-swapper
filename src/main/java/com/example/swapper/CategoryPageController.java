package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Category;
import service.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;

public class CategoryPageController {
    @FXML
    private ImageView categories;

    @FXML
    public void backMain() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    @FXML
    public void initialize() {
        File file = new File("src/main/resources/com/example/swapper/views/images/" + Category.getNameOfCategory() + ".png");
        Image image = new Image(file.toURI().toString());
        categories.setImage(image);
    }

    public void redirectToFilterPage() throws IOException {
        SwitchScreen.changeScreen("views/filter.fxml");
    }
}
