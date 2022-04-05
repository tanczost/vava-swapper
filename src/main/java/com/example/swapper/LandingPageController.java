package com.example.swapper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Category;
import models.Filter;
import models.Product;
import service.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LandingPageController {
    @FXML
    public ImageView ivAvatar;
    @FXML
    public ImageView ivTopProduct;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Label lbCategories;
    @FXML
    private TextField tfSearch;
    Product topProduct = null;


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        Category.setNameOfCategory(Item.getId());
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }


    @FXML
    public void initialize() throws Exception {
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

        ResultSet topProductRaw = ProductDbServices.getTopProduct();
        if (topProductRaw != null) {
            while (topProductRaw.next()) {
                topProduct = new Product(
                        topProductRaw.getInt(1),
                        topProductRaw.getString(2),
                        topProductRaw.getString(3),
                        topProductRaw.getBoolean(4),
                        topProductRaw.getInt(5)
                );
            }

            InputStream is = FileHandler.getFile(topProduct.getImgId());
            System.out.println(topProduct);
            ivTopProduct.setImage(new Image(is));
        }

    }

    @FXML
    public void searchForProduct() throws IOException {
        Filter.setSearchInput(tfSearch.getText());
        Category.setNameOfCategory("");
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    @FXML
    public void clearSearch() {
        tfSearch.setText(null);
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

    public void topProductSelected() throws IOException {
        Account.setCurrentProduct(topProduct);
        SwitchScreen.changeScreen("views/SelectProposition.fxml");

    }
}

