package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import service.FileHandler;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class OfferDetailController {
    @FXML
    public ImageView ivProduct;
    @FXML
    public Label lbProduct;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbUserProductLabel;
    @FXML
    public Label lbTopBadge;
    @FXML
    public Label lbUserProduct;
    @FXML
    public Button btnDecline;
    @FXML
    public Button btnBack;
    private Account account = Account.getInstance();

    @FXML
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbUserProduct.setText(account.getCurrentOffer().getName());
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
        btnDecline.setText(resourceBundle.getString("decline"));
        btnBack.setText(resourceBundle.getString("back"));

        lbProduct.setText(account.getCurrentOffer().getName());
        lbDescription.setText(account.getCurrentOffer().getDescription());

        InputStream is = FileHandler.getFile(account.getCurrentOffer().getImgId());
        ivProduct.setImage(new Image(is));

    }

    public void cancelOffer() {
        //TODO dorobit
    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/OfferPage.fxml");
    }
}
