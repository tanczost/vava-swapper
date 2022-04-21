package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Account;
import models.Admin;
import models.Product;
import observer.Observer;
import observer.Subject;
import service.common.UIHelper;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminPageController extends Subject {
    @FXML
    public ListView<String> lvAllProducts;
    @FXML
    public Button btnLogout;
    @FXML
    public Label lbItems;
    private List<Product> allProducts = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {
        this.attach(HelloApplication.getLogManager());
        this.notifyObserver("Admin page loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnLogout.setText(resourceBundle.getString("logout"));
        lbItems.setText(resourceBundle.getString("allItems"));
        ResultSet result = ProductDbServices.getAllProduct();
        UIHelper.mapResultSetToProducts(result, allProducts, lvAllProducts);
    }

    
    public void productSelected() throws IOException {
        if (lvAllProducts.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lvAllProducts.getSelectionModel().getSelectedIndex();
        Admin.getInstance().setSelectedProduct(allProducts.get(offersIndex));

        SwitchScreen.changeScreen("views/adminDelete.fxml");
    }

    public void logout() throws IOException {
        Account.getInstance().logout();
        this.notifyObserver("User logged out.", Observer.LEVEL.info);
        SwitchScreen.changeScreen("views/login.fxml");
    }

}
