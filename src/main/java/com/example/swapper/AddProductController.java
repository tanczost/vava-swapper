package com.example.swapper;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import models.Account;
import service.FileHandler;
import service.db.ProductDbServices;

public class AddProductController {
    final FileChooser fileChooser = new FileChooser();
    private String imgPath;

    @FXML
    private void chooseImg(){

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        imgPath = fileChooser.showOpenDialog(HelloApplication.stage).getAbsolutePath();
        if (imgPath == null) {
            System.out.println("Img not choosed do something");
        }

        //TODO inform user that image is selected
    }

    @FXML
    private void addProduct() throws Exception {
        int loggedUserId = Account.getLoggedUserId();
        //TODO: get info from input fields, file uploader create

        int newImageId = FileHandler.uploadFile(FileHandler.readImageToByteStream(imgPath));
        String productName = "super product";
        String productDescription = "need to have hahahaha";
        boolean topped = true;

        int result = ProductDbServices.insertProductDb(productName, productDescription, topped, loggedUserId, newImageId);

        if(result > 0){
            System.out.println("Succes do something");
        }
        else{
            System.out.println("Not succes do something");
        }

        System.out.println("hello");
    }
}
