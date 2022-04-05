package com.example.swapper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Category;
import models.Product;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LandingPageController {
    @FXML
    public ImageView ivAvatar;
    @FXML
    public ListView lvTopProducts;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Label lbCategories;


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        Category.setNameOfCategory(Item.getId());
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }


    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbCategories.setText(resourceBundle.getString("category"));
        if (Account.getCurrentUser() == null) {
            btnLogin.setText(resourceBundle.getString("login"));
            btnLogin.setVisible(true);
            ivAvatar.setVisible(false);
        } else {
            btnAddProduct.setText(resourceBundle.getString("addProduct"));
            btnAddProduct.setVisible(true);
        }

        //TODO set top products in lvTopProducts
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    public void navigateToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void redirectToLogin() throws IOException {
        SwitchScreen.changeScreen("views/login.fxml");
    }

    public void topProductSelected() throws SQLException, IOException {
        for (Product product : Account.getProductsOfLoggedUser()) {
            if (product.getName().equals(lvTopProducts.getSelectionModel().getSelectedItem().toString())) {
                Account.setCurrentProduct(product);
                SwitchScreen.changeScreen("views/SelectProposition.fxml");
                break;
            }
        }
    }
}
