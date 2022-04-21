package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Filter;
import models.Product;
import observer.Observer;
import observer.Subject;
import service.common.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LandingPageController extends Subject {
    @FXML
    public ImageView ivAvatar;
    @FXML
    public ImageView ivTopProduct;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Label lbCategories;
    @FXML
    private TextField tfSearch;
    private Product topProduct = null;
    private Account account = Account.getInstance();
    private Filter filter = Filter.getInstance();


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        filter.setCategory(Item.getId());
        SwitchScreen.changeScreen("views/CategoryPage.fxml");
    }


    @FXML
    public void initialize() throws Exception {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Landing page loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbCategories.setText(resourceBundle.getString("category"));
        if (account.getCurrentUser() == null) {
            btnLogin.setText(resourceBundle.getString("login"));
            btnLogin.setVisible(true);
            ivAvatar.setVisible(false);
        } else {
            btnAddProduct.setText(resourceBundle.getString("addProduct"));
            btnAddProduct.setVisible(true);
        }

        ResultSet topProductRaw = ProductDbServices.getTopProduct();
        if (topProductRaw != null) {
            while (topProductRaw.next()) {
                topProduct = new Product(
                        topProductRaw.getInt(1),
                        topProductRaw.getString(2),
                        topProductRaw.getString(3),
                        topProductRaw.getBoolean(4),
                        topProductRaw.getInt(5),
                        topProductRaw.getTimestamp(8)
                );
            }
            InputStream is = FileHandler.getFile(topProduct.getImgId());
            ivTopProduct.setImage(new Image(is));
        }

    }

    @FXML
    public void searchForProduct() throws IOException {
        filter.setSearchInput(tfSearch.getText());
        filter.setCategory("");
        SwitchScreen.changeScreen("views/CategoryPage.fxml");
    }

    @FXML
    public void clearSearch() {
        tfSearch.setText(null);
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/AddProductModal.fxml");
    }

    public void navigateToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void redirectToLogin() throws IOException {
        SwitchScreen.changeScreen("views/LoginPage.fxml");
    }

    public void topProductSelected() throws IOException {
        if (account.getCurrentUser() != null) {
            account.setCurrentProduct(topProduct);
            SwitchScreen.changeScreen("views/SelectPropositionModal.fxml");
        }
    }
}

