package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Account;
import service.Auth;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField pfPassword;

    @FXML
    private Label lError;

    @FXML
    private void login() throws SQLException, IOException {
        if (!tfUsername.getText().isEmpty() && !pfPassword.getText().isEmpty()) {
            boolean success = Auth.login(tfUsername.getText(), pfPassword.getText());
            System.out.println(Account.checkLogin());

            if (success) {
                Account.loadProducts();
                SwitchScreen.changeScreen("views/landingPage.fxml");
                System.out.println(tfUsername.getText() + " has succesfully logged in");
            } else {
                lError.setText("Bad login credentials!");
                System.out.println("Bad login credentials from " + tfUsername.getText());
            }
        } else {
            lError.setText("Enter your login details!");
        }
    }

    @FXML
    private void register() throws IOException {
        SwitchScreen.changeScreen("views/register.fxml");
    }
}
