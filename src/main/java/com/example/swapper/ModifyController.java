package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyController {
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnMainPage;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfDescription;
    @FXML
    private RadioButton rbTop;


    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnUpdate.setText(resourceBundle.getString("update"));
    }

    @FXML
    private void updateProduct() throws SQLException {

        int result = ProductDbServices.updateProduct(1, tfProductName.getText(), tfDescription.getText(), rbTop.isSelected());

        if (result > 0) {
            System.out.println("Success do something");
        } else {
            System.out.println("Not  success do something");
        }
    }

    @FXML
    public void BackToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }
}
