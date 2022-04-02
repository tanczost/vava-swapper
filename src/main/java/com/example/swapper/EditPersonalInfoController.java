package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Account;
import models.User;
import service.navigation.SwitchScreen;
import service.validation.Validator;

import java.io.IOException;
import java.util.ResourceBundle;

public class EditPersonalInfoController {
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfNickName;
    @FXML
    private TextField tfTown;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfSchool;
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbPassword;
    @FXML
    private Label lbNickName;
    @FXML
    private Label lbTown;
    @FXML
    private Label lbStreet;
    @FXML
    private Label lbSchool;
    @FXML
    private Label lMessage;
    @FXML
    private Button btnUpdate;
    @FXML
    public Button btnBack;

    @FXML
    public void initialize() {
        User currentUser = Account.getCurrentUser();
        tfFirstName.setText(currentUser.getFirstName());
        tfLastName.setText(currentUser.getLastName());
        tfNickName.setText(currentUser.getNickName());
        tfTown.setText(currentUser.getTown());
        tfEmail.setText(currentUser.getEmail());
        tfStreet.setText(currentUser.getStreet());
        tfSchool.setText(currentUser.getSchool());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbFirstName.setText(resourceBundle.getString("firstname"));
        lbLastName.setText(resourceBundle.getString("lastname"));
        lbPassword.setText(resourceBundle.getString("password"));
        lbNickName.setText(resourceBundle.getString("nickname"));
        lbTown.setText(resourceBundle.getString("town"));
        lbStreet.setText(resourceBundle.getString("street"));
        lbSchool.setText(resourceBundle.getString("school"));
        btnUpdate.setText(resourceBundle.getString("update"));
        btnBack.setText(resourceBundle.getString("back"));
    }

    @FXML
    private void updateInfo() throws Exception {
        if (!tfFirstName.getText().isEmpty() && !tfNickName.getText().isEmpty()
                && !tfEmail.getText().isEmpty() && !tfPassword.getText().isEmpty()
                && !tfLastName.getText().isEmpty() && !tfTown.getText().isEmpty()
                && !tfStreet.getText().isEmpty() && !tfSchool.getText().isEmpty()
        ) {
            if (Validator.validDbInputEmail(tfEmail.getText(), lMessage)
                    && Validator.validRegisterPassword(tfPassword.getText(), lMessage)
            ) {

//                boolean result = Auth.registration(tfNickName.getText(),
//                        tfFirstName.getText(), tfLastName.getText(),
//                        tfEmail.getText(), tfTown.getText(),
//                        tfStreet.getText(), tfSchool.getText(),
//                        tfPassword.getText());
//
//                if (result) {
//                    System.out.println("Successfully registered user");
//                    SwitchScreen.changeScreen("views/login.fxml");
//                } else {
//                    System.out.println("Registration was aborted");
//                }
            }
        } else {
            lMessage.setText("You have empty fields");
        }
    }

    public void backToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
