package com.example.swapper;

import javafx.fxml.FXML;
import models.Account;
import service.FileHandler;
import service.db.ProductDbServices;

public class AddProductController {
    @FXML

    private void addProduct() throws Exception {
        int loggedUserId = Account.getLoggedUserId();
        //TODO: get info from input fields, file uploader create

        String imgPath = "/Users/tanczi/Desktop/swapper/src/main/resources/test.png";
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
