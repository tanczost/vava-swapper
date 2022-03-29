package com.example.swapper;

import javafx.fxml.FXML;
import service.db.ProductDbServices;

import java.sql.SQLException;

public class ModifyController {

    @FXML
    private void updateProduct() throws SQLException {

        //TODO get strings from fields
        String newName = "new name";
        String newDescription = "new description";
        boolean isTopped = true;
        int result = ProductDbServices.updateProduct(1,newName, newDescription,  isTopped);

        if(result> 0){
            System.out.println("Success do something");
        }
        else{
            System.out.println("Not  success do something");
        }
    }
}
