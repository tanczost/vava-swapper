package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.Account;
import models.Product;
import service.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import java.io.InputStream;
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
    private ImageView imgView;


    @FXML
    public void initialize() throws Exception {
        Product currentProduct = Account.getCurrentProduct();
        tfProductName.setText(currentProduct.getName());
        tfDescription.setText(currentProduct.getDescription());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnProposalPage.setText(resourceBundle.getString("myProposals"));
        btnUpdate.setText(resourceBundle.getString("update"));
        rbTop.setSelected(currentProduct.isTopped());
        InputStream is = FileHandler.getFile(currentProduct.getImgId());
        Image img = new Image(is);
        imgView.setImage(img);
    }

    @FXML
    private void updateProduct() throws SQLException, IOException {

        int result = ProductDbServices.updateProduct(Account.getCurrentProduct().getId(), tfProductName.getText(), tfDescription.getText(), rbTop.isSelected());

        if (result > 0) {
            SwitchScreen.changeScreen("views/ProposalPage.fxml");
        } else {
            System.out.println("Not  success do something");
        }

        Account.loadProducts();
    }

    @FXML
    public void BackToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
