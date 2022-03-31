package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.Auth;
import service.navigation.SwitchScreen;
import service.validation.Validator;

public class RegisterController {
    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfTown;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfSchool;

    @FXML
    private Label lMessage;

    @FXML
    private void registration() throws Exception {
        if (!tfFirstName.getText().isEmpty() && !tfUserName.getText().isEmpty()
                && !tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()
                && !tfLastName.getText().isEmpty() && !tfTown.getText().isEmpty()
                && !tfStreet.getText().isEmpty() && !tfSchool.getText().isEmpty()
        ) {
            if (Validator.validDbInputEmail(tfEmail.getText(), lMessage)
                    && Validator.validRegisterPassword(tfPassword.getText(), lMessage)
            ) {

                boolean result = Auth.registration(tfUserName.getText(),
                        tfFirstName.getText(), tfLastName.getText(),
                        tfEmail.getText(), tfTown.getText(),
                        tfStreet.getText(), tfSchool.getText(),
                        tfPassword.getText());

                if (result) {
                    System.out.println("Successfully registered user");
                    SwitchScreen.changeScreen("views/login.fxml");
                } else {
                    System.out.println("Registration was aborted");
                }
            }
        } else {
            lMessage.setText("You have empty fields");
        }
    }
}
