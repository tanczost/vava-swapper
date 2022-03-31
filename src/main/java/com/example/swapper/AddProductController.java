package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Account;
import service.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;

public class AddProductController {
    final FileChooser fileChooser = new FileChooser();
    private String imgPath;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfDescription;
    @FXML
    private RadioButton rbTop;
    @FXML
    private ComboBox cbCategory;

    @FXML
    public void initialize() {
        String categories[] = {"T-shirt", "Pants", "Hoodies", "Accessories", "Coats", "Boots"};
        for (String category : categories) {
            cbCategory.getItems().add(category);
        }
    }


    @FXML
    private void chooseImg() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        try {
            imgPath = fileChooser.showOpenDialog(HelloApplication.stage).getAbsolutePath();
            if (imgPath == null) {
                System.out.println("Img not choosed do something");
            }
        } catch (Exception e) {
            if (imgPath == null) {
                System.out.println("Img not choosed do something");
            }
            System.out.println(e);
        }


        //TODO inform user that image is selected
    }

    @FXML
    private void addProduct() throws Exception {
        //TODO: file uploader create
        int loggedUserId = Account.getLoggedUserId();
        //TODO:  file uploader create
        int newImageId = FileHandler.uploadFile(FileHandler.readImageToByteStream(imgPath));
        int result = ProductDbServices.insertProductDb(tfProductName.getText(), tfDescription.getText(), rbTop.isSelected(), loggedUserId, newImageId, cbCategory.getValue().toString());

        if (result > 0) {
            SwitchScreen.changeScreen("views/landingPage.fxml");
        } else {
            System.out.println("Not succes do something");
        }

        System.out.println("hello");
    }

    @FXML
    public void BackToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }
}
