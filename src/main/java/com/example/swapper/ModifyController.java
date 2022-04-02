package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.Account;
import models.Product;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyController {
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnProposalPage;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfDescription;
    @FXML
    private RadioButton rbTop;


    @FXML
    public void initialize() {
        Product currentProduct = Account.getCurrentProduct();
        tfProductName.setText(currentProduct.getName());
        tfDescription.setText(currentProduct.getDescription());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnProposalPage.setText(resourceBundle.getString("myProposals"));
        btnUpdate.setText(resourceBundle.getString("update"));
    }

    @FXML
    private void updateProduct() throws SQLException, IOException {

        int result = ProductDbServices.updateProduct(Account.getCurrentProduct().getId(), tfProductName.getText(), tfDescription.getText(), rbTop.isSelected());

        if (result > 0) {
            SwitchScreen.changeScreen("views/ProposalPage.fxml");
        } else {
            System.out.println("Not  success do something");
        }
    }

    @FXML
    public void BackToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
