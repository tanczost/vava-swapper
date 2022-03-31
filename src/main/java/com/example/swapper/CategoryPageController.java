package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Category;

import java.io.File;
import java.io.IOException;

public class CategoryPageController {
    @FXML
    private ImageView categories;

    @FXML
    public void backMain() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("views/landingPage.fxml");
    }

    @FXML
    public void initialize() {
        File file = new File("src/main/resources/com/example/swapper/views/images/" + Category.getInstance().getNameOfCategory() + ".png");
        Image image = new Image(file.toURI().toString());
        categories.setImage(image);
    }
}
