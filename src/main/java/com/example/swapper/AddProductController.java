package com.example.swapper;

import javafx.fxml.FXML;
import models.Account;
import service.db.ProductDbServices;

public class AddProductController {
    @FXML

    private void addProduct(){
        int loggedUserId = Account.getLoggedUserId();
        //TODO: get info from input fields, file uploader create
        String productName = "super product";
        String productDescription = "need to have hahahaha";
        boolean topped = true;

        int result = ProductDbServices.insertProductDb(productName, productDescription, topped, loggedUserId);

        if(result > 0){
            System.out.println("Succes do something");
        }
        else{
            System.out.println("Not succes do something");
        }

        System.out.println("hello");
    }
}
