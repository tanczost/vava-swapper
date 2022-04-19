package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Account;
import service.Auth;
import service.LogManager;
import service.db.UserDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    public Button btnMainPage;
    @FXML
    private TextField tfNickname;
    @FXML
    private TextField pfPassword;
    @FXML
    private Label lError;
    @FXML
    private Label lbNickname;
    @FXML
    private Label lbPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnNoAccount;
    private Account account = Account.getInstance();

    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbNickname.setText(resourceBundle.getString("nickname"));
        lbPassword.setText(resourceBundle.getString("password"));
        btnLogIn.setText(resourceBundle.getString("login"));
        btnNoAccount.setText(resourceBundle.getString("noAccount"));
        btnMainPage.setText(resourceBundle.getString("mainPage"));
    }

    @FXML
    private void login() throws SQLException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        if (!tfNickname.getText().isEmpty() && !pfPassword.getText().isEmpty()) {
            boolean success = Auth.login(tfNickname.getText(), pfPassword.getText());

            if (success) {
                if (UserDbServices.isUserAdmin(account.getLoggedUserId())) {
                    SwitchScreen.changeScreen("views/AdminPage.fxml");
                    return;
                }
                account.loadProducts();
                SwitchScreen.changeScreen("views/landingPage.fxml");
                System.out.println(tfNickname.getText() + " has succesfully logged in");
            } else {
                lError.setText(resourceBundle.getString("badLogin"));
                System.out.println("Bad login credentials from " + tfNickname.getText());
            }
        } else {
            lError.setText(resourceBundle.getString("enterDetails"));
        }
    }

    @FXML
    private void register() throws IOException {
        SwitchScreen.changeScreen("views/register.fxml");
    }

    public void navigateToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }
}
