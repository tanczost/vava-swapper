package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import models.Product;
import service.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class SelectPropositionController {
    @FXML
    public Button btnTrade;
    @FXML
    public ImageView ivProduct;
    @FXML
    public Label lbProduct;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbTopBadge;
    @FXML
    public Label lbUserProductLabel;
    @FXML
    public ComboBox cbUserItems;
    @FXML
    public Button btnBack;

    @FXML
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
        btnBack.setText(resourceBundle.getString("back"));


        lbProduct.setText(Account.getCurrentProduct().getName());
        lbDescription.setText(Account.getCurrentProduct().getDescription());

        InputStream is = FileHandler.getFile(Account.getCurrentProduct().getImgId());
        ivProduct.setImage(new Image(is));

        for (Product product : Account.getProductsOfLoggedUser()) {
            cbUserItems.getItems().add(product.getName());
        }

    }

    public void trade() throws IOException {
        ProductDbServices.insertOfferDb(Account.getCurrentProduct().getId(), Account.getCurrentOffer().getId());
        Account.setCurrentOffer(null);
        Account.setCurrentProduct(null);
        SwitchScreen.changeScreen("views/landingPage.fxml");

    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }
}
