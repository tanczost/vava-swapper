package com.example.swapper;

import javafx.fxml.FXML;
import service.Auth;

public class RegisterController {
    @FXML

    private void registration() throws Exception {
        //TODO: add input text from textfield and validate them (there should be package fx validator)
        boolean result = Auth.registration("tanczi", "tomi","tanczos", "tanczost23gmail.com",
        "kosuty", "kosuty", "fiit", "p@$$word");

        if(result){
            System.out.println("Successfully registered user");
        }
        else{
            System.out.println("Registration was aborted");
        }
    }
}
