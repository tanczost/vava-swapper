package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import service.common.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
    public Button btnDecline;
    @FXML
    public Button btnBack;
    @FXML
    public Label lTop;
    private Account account = Account.getInstance();


    @FXML
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProduct.setText(account.getCurrentProduct().getName());
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
        btnDecline.setText(resourceBundle.getString("decline"));
        btnBack.setText(resourceBundle.getString("back"));

        //TODO add label for TOP product -- maybe its done
        lTop.setText("");
        if (account.getCurrentOffer().isTopped()) {
            lTop.setText("TOP");
        }

        lbProduct.setText(account.getCurrentOffer().getName());
        lbDescription.setText(account.getCurrentOffer().getDescription());

        InputStream is = FileHandler.getFile(account.getCurrentOffer().getImgId());
        ivProduct.setImage(new Image(is));

    }

    public void trade() throws SQLException, IOException {
        ProductDbServices.tradeProduct(account.getCurrentProduct().getId(), account.getCurrentOffer().getId());
        account.setCurrentOffer(null);
        account.setCurrentProduct(null);
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    public void declineProposal() {
    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
