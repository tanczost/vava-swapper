package com.example.swapper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Category;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.util.ResourceBundle;


public class LandingPageController {
    @FXML
    public ImageView ivAvatar;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnAddProduct;


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        Category.setNameOfCategory(Item.getId());
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }


    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        if (Account.getCurrentUser() == null) {
            btnLogin.setText(resourceBundle.getString("login"));
            btnLogin.setVisible(true);
            ivAvatar.setVisible(false);
        } else {
            btnAddProduct.setText(resourceBundle.getString("addProduct"));
            btnAddProduct.setVisible(true);
        }
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    public void navigateToProposalPage(MouseEvent mouseEvent) throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void redirectToLogin() throws IOException {
        SwitchScreen.changeScreen("views/login.fxml");
    }
}
