package com.example.swapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Account;
import service.FileHandler;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ResourceBundle;


public class PropositionController {
    @FXML
    public ImageView ivProduct;
    @FXML
    public Button btnTrade;
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
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProduct.setText(Account.getCurrentProduct().getName());
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));

        lbProduct.setText(Account.getCurrentOffer().getName());
        lbDescription.setText(Account.getCurrentOffer().getDescription());

        InputStream is = FileHandler.getFile(Account.getCurrentOffer().getImgId());
        Image img = new Image(is);
        ivProduct.setImage(img);

    }

    public void trade(ActionEvent actionEvent) {
    }
}
