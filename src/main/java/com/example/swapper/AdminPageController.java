package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Account;
import models.Product;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminPageController {
    @FXML
    public ListView<String> lvAllProducts;
    @FXML
    public Button btnLogout;
    private ArrayList<Product> allProducts = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {
        ResultSet result = ProductDbServices.getAllProduct();

        mapResultSetToProducts(result, allProducts, lvAllProducts);
    }

    static void mapResultSetToProducts(ResultSet result, ArrayList<Product> allProducts, ListView<String> lvAllProducts) throws SQLException {
        if (result == null) {
            return;
        }
        while (result.next()) {
            allProducts.add(new Product(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBoolean(4),
                    result.getInt(5),
                    result.getTimestamp(8)
            ));
        }

        allProducts.forEach(e -> {
            lvAllProducts.getItems().add(e.toString());
        });
    }

    public void productSelected() throws IOException {
        //TODO logic for selection(ADMIN model)
        SwitchScreen.changeScreen("views/adminDelete.fxml");
    }

    public void logout() throws IOException {
        Account.logout();
        SwitchScreen.changeScreen("views/login.fxml");
    }

    //TODO fix icon in  corner
}
