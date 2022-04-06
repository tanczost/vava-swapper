package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.util.ResourceBundle;

public class AdminDeleteController {
    @FXML
    public Label lbProductName;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbCategory;
    @FXML
    public Button btnBack;
    @FXML
    public Button btnDelete;

    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnBack.setText(resourceBundle.getString("back"));
        btnDelete.setText(resourceBundle.getString("delete"));
        //TODO load product properties
    }

    public void deleteProduct() {
        //TODO logic for delete
    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/AdminPage.fxml");
    }
}
