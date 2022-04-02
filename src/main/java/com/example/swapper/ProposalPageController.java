package com.example.swapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Account;
import models.Product;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
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

    public void redirectToUpdateProduct() throws IOException, SQLException {
        for (Product product : Account.getProductsOfLoggedUser()) {
            if (product.getName().equals(cbUserItems.getValue().toString())) {
                Account.setCurrentProduct(product);
                SwitchScreen.changeScreen("views/modifyProduct.fxml");
                break;
            }
        }
    }
}
