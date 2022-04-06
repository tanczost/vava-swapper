package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.util.ArrayList;


public class AdminPageController {
    @FXML
    public ListView lvAllProducts;
    @FXML
    public Button btnLogout;
    private ArrayList allProducts = new ArrayList<>();

    @FXML
    public void initialize() {
        //TODO load all products
    }

    public void productSelected() throws IOException {
        //TODO logic for selection(ADMIN model)
        SwitchScreen.changeScreen("views/adminDelete.fxml");
    }

    public void logout() throws IOException {
        //TODO logic for logout
        SwitchScreen.changeScreen("views/login.fxml");
    }
}
