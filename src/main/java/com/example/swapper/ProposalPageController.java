package com.example.swapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Account;
import models.Product;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProposalPageController {
    @FXML
    public Button btnOffers;
    @FXML
    public Button btnProposals;
    @FXML
    public Button btnUpdateProduct;
    @FXML
    public ListView lvUserItems;
    @FXML
    public Button btnLogout;
    @FXML
    private ComboBox cbUserItems;
    @FXML
    private Label lbName;
    @FXML
    private Label lbItems;
    @FXML
    private Button btnEditInfo;
    @FXML
    private Button btnMainPage;
    @FXML
    private Button btnToggleLanguage;
    @FXML
    public Button addProduct;

    private ArrayList<Product> offersForProduct = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbItems.setText(resourceBundle.getString("yourItems"));
        btnEditInfo.setText(resourceBundle.getString("editInfo"));
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnToggleLanguage.setText(resourceBundle.getString("toggleLanguage"));
        btnOffers.setText(resourceBundle.getString("myOffers"));
        btnProposals.setText(resourceBundle.getString("myProposals"));
        addProduct.setText(resourceBundle.getString("addProduct"));
        btnUpdateProduct.setText(resourceBundle.getString("updateProduct"));
        btnLogout.setText(resourceBundle.getString("logout"));


        lbName.setText(Account.getCurrentUser().getNickName().toUpperCase(Locale.ROOT));
        for (Product product : Account.getProductsOfLoggedUser()) {
            cbUserItems.getItems().add(product.getName());
        }
    }

    public void toggleLanguage() throws IOException {
        boolean hasEnglishSelected = Account.getCurrentUser().hasEnglishLanguageSelected();
        if (hasEnglishSelected) {
            Locale.setDefault(new Locale("sk", "SK"));
        } else {
            Locale.setDefault(new Locale("en", "EN"));
        }
        Account.getCurrentUser().setHasEnglishLanguageSelected(!hasEnglishSelected);
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    public void redirectToMainPage(ActionEvent event) throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    public void redirectToEditPersonalInfo() throws IOException {
        SwitchScreen.changeScreen("views/editPersonalInfo.fxml");
    }

    public void redirectToUpdateProduct() throws IOException {
        if (cbUserItems.getSelectionModel().isEmpty()) {
            return;
        }
        SwitchScreen.changeScreen("views/modifyProduct.fxml");
    }

    public void redirectToOfferPage() throws IOException {
        SwitchScreen.changeScreen("views/OfferPage.fxml");
    }

    public void offerSelected() throws IOException {
        if (lvUserItems.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lvUserItems.getSelectionModel().getSelectedIndex();
        Account.setCurrentOffer(offersForProduct.get(offersIndex));

        SwitchScreen.changeScreen("views/proposition.fxml");
    }

    public void productSelected() throws SQLException {
        for (Product product : Account.getProductsOfLoggedUser()) {
            if (product.getName().equals(cbUserItems.getValue().toString())) {
                Account.setCurrentProduct(product);
                break;
            }
        }

        ResultSet offers = ProductDbServices.getOffersForProduct(Account.getCurrentProduct().getId());

        lvUserItems.getItems().clear();
        if (offers == null) {
            return;
        }

        offersForProduct.clear();


        while (offers.next()) {
            offersForProduct.add(new Product(
                    offers.getInt(1),
                    offers.getString(2),
                    offers.getString(3),
                    offers.getBoolean(4),
                    offers.getInt(5),
                    offers.getTimestamp(7)
            ));
        }

        offersForProduct.forEach(e -> {
            lvUserItems.getItems().add(e.toString());
        });


    }

    public void logout() throws IOException {
        Account.logout();
        SwitchScreen.changeScreen("views/login.fxml");
    }
}
