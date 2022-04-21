package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import models.Product;
import observer.Observer;
import observer.Subject;
import service.common.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyController extends Subject {
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
    private Account account = Account.getInstance();


    @FXML
    public void initialize() throws Exception {
        this.attach(HelloApplication.getLogManager());
        this.notifyObserver("Page for modifying loaded.", Observer.LEVEL.info);

        Product currentProduct = account.getCurrentProduct();
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

        int result = ProductDbServices.updateProduct(account.getCurrentProduct().getId(), tfProductName.getText(), tfDescription.getText(), rbTop.isSelected());

        if (result > 0) {
            this.notifyObserver("Product updated.", Observer.LEVEL.info);
            SwitchScreen.changeScreen("views/ProposalPage.fxml");
        } else {
            this.notifyObserver("Failed to update product.", Observer.LEVEL.warning);
            System.out.println("Not  success do something");
        }

        account.loadProducts();
    }

    @FXML
    public void BackToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
