package com.example.swapper;

import javafx.fxml.FXML;
import models.Account;
import service.Auth;

import java.sql.SQLException;

public class LoginController {
    @FXML

    private void login() throws SQLException {
        //TODO: add input text from textfield and validate them (there should be package fx validator)

        System.out.println(Account.checkLogin());

        boolean success = Auth.login("tanczi", "password");

        System.out.println(Account.checkLogin());

        if(success){ // TODO redirect user to home page
            System.out.println("do something");
        }
        else{ // TODO inform user that login was not successful
            System.out.println("do something");
        }

    }
}
