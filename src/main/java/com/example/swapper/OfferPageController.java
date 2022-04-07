package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Account;
import models.Product;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OfferPageController {
    @FXML
    public Button addProduct;
    @FXML
    public Button btnMainPage;
    @FXML
    public Button btnOffers;
    @FXML
    public Button btnProposals;
    @FXML
    public ListView lvOffers;

    private ArrayList<Product> offersForProduct = new ArrayList<>();

    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    @FXML
    public void initialize() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnOffers.setText(resourceBundle.getString("myOffers"));
        btnProposals.setText(resourceBundle.getString("myProposals"));
        addProduct.setText(resourceBundle.getString("addProduct"));

        allProposals();
    }

    public void allProposals() throws SQLException {
        //TODO connect to BE
        offersForProduct.clear();
        Product product = new Product(5, "Sajt", "Szep", true, 5, new Timestamp(new Date(2012, 12, 12).getTime()));
        offersForProduct.add(product);


        offersForProduct.forEach(e -> {
            lvOffers.getItems().add(e.toString());
        });
    }

    public void offerSelected() throws IOException {
        if (lvOffers.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lvOffers.getSelectionModel().getSelectedIndex();
        System.out.println(offersForProduct.get(offersIndex).toString());
        Account.setCurrentOffer(offersForProduct.get(offersIndex));

        SwitchScreen.changeScreen("views/OfferDetail.fxml");
    }

    public void redirectToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    public void redirectToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
