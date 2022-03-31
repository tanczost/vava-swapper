package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Account;
import models.Product;

import java.sql.SQLException;
import java.util.Locale;

public class ProposalPageController {

    @FXML
    private ComboBox cbUserItems;
    @FXML
    private Label lbName;

    @FXML
    public void initialize() throws SQLException {
        lbName.setText(Account.getCurrentUser().getNickName().toUpperCase(Locale.ROOT));
        for (Product product : Account.getProductsOfLoggedUser()) {
            cbUserItems.getItems().add(product.getName());
        }
    }
}
