package com.example.swapper;

import javafx.fxml.FXML;
import service.Auth;

public class RegisterController {
    @FXML

    private void registration(){
        //TODO: add input text from textfield and validate them (there should be package fx validator)
        boolean result = Auth.registration("tanczi", "tomi","tanczos", "1@gmail.com",
        "kosuty", "kosuty", "fiit stu", "password");

        if(result){
            System.out.println("Successfully registered user");
        }
        else{
            System.out.println("Registration was aborted");
        }
    }
}
