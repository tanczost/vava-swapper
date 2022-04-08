package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Account;
import service.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class AddProductController {
    @FXML
    public Button btnUploadImage;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnMainPage;
    @FXML
    public Label lbCategory;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfDescription;
    @FXML
    private RadioButton rbTop;
    @FXML
    private ComboBox cbCategory;
    @FXML
    private ImageView imgView;

    final FileChooser fileChooser = new FileChooser();
    private String imgPath;
    private String imgName;
    private File file;

    @FXML
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnAdd.setText(resourceBundle.getString("add"));
        btnUploadImage.setText(resourceBundle.getString("uploadImage"));
        lbCategory.setText(resourceBundle.getString("category"));

        String categories[] = {"Shirt", "Pant", "Hoody", "Accessory", "Coat", "Boot"};
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
            file = fileChooser.showOpenDialog(HelloApplication.stage);
            imgPath = file.getAbsolutePath();
            imgName = file.getName();

        } catch (Exception e) {
            if (imgPath == null) {
                System.out.println("Img not choosed do something");
            }
            System.out.println(e);
        }

        imgView.setImage(new Image("file://" + imgPath));

    }

    @FXML
    private void addProduct() throws Exception {
        int loggedUserId = Account.getLoggedUserId();
        int newImageId = FileHandler.uploadFile(FileHandler.readImageToByteStream(imgPath, imgName));
        int result = ProductDbServices.insertProductDb(tfProductName.getText(), tfDescription.getText(), rbTop.isSelected(), loggedUserId, newImageId, cbCategory.getValue().toString());

        if (result > 0) {
            SwitchScreen.changeScreen("views/landingPage.fxml");
        } else {
            System.out.println("Not succes do something");
        }
    }

    @FXML
    public void BackToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    //TODO fix showing image when uploading
}
