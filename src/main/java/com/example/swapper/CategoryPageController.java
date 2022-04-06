package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import models.Category;
import models.Filter;
import models.Product;
import service.ProductComparator;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoryPageController {
    @FXML
    public Button btnLogin;
    @FXML
    public Button btnAddProduct;
    @FXML
    public ImageView ivAvatar;
    @FXML
    public Label lbSort;
    @FXML
    private ImageView categories;
    @FXML
    private TextField tfSearch;
    @FXML
    private ListView lwCategoryItems;


    private ArrayList<Product> filteredProducts = new ArrayList<>();

    @FXML
    public void backMain() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    @FXML
    public void initialize() throws SQLException {
        File file = new File("src/main/resources/com/example/swapper/views/images/" + Category.getNameOfCategory() + ".png");
        Image image = new Image(file.toURI().toString());
        categories.setImage(image);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbSort.setText(resourceBundle.getString("sortByDate"));
        if (Account.getCurrentUser() == null) {
            btnLogin.setText(resourceBundle.getString("login"));
            btnLogin.setVisible(true);
            ivAvatar.setVisible(false);
        } else {
            btnAddProduct.setText(resourceBundle.getString("addProduct"));
            btnAddProduct.setVisible(true);
        }

        //if user searched by searchbar
        if (Filter.getSearchInput() != null) {
            ResultSet result = ProductDbServices.getProductsByName(Filter.getSearchInput());

            if (result == null) {
                return;
            }
            while (result.next()) {
                filteredProducts.add(new Product(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getBoolean(4),
                        result.getInt(5),
                        result.getTimestamp(8)
                ));
            }

            filteredProducts.forEach(e -> {
                lwCategoryItems.getItems().add(e.toString());
            });
            System.out.println(filteredProducts);
            //else search by categories
        } else {
            ResultSet productsByCategory = ProductDbServices.getProductsByCategory("Hoodies");
        }

    }

    @FXML
    public void searchForProduct() throws IOException {
        Filter.setSearchInput(tfSearch.getText());
        Category.setNameOfCategory("");
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    @FXML
    public void clearSearch() {
        tfSearch.setText(null);
    }

    public void offerSelected() throws IOException {
        if (lwCategoryItems.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lwCategoryItems.getSelectionModel().getSelectedIndex();
        Account.setCurrentProduct(filteredProducts.get(offersIndex));
        SwitchScreen.changeScreen("views/SelectProposition.fxml");
    }

    public void redirectToFilterPage() throws IOException {
        SwitchScreen.changeScreen("views/filter.fxml");
    }

    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    public void redirectToLogin() throws IOException {
        SwitchScreen.changeScreen("views/login.fxml");
    }

    public void setDateASC() {
        System.out.println("asc");

        filteredProducts.sort(new ProductComparator());


        filteredProducts.forEach(e -> System.out.println(e.toString()));

    }

    public void setDateDESC() {

        System.out.println("desc");

        filteredProducts.sort(new ProductComparator().reversed());


        filteredProducts.forEach(e -> System.out.println(e.toString()));
    }
}
