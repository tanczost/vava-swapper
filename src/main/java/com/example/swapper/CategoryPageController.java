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
import service.UIHelper;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private Account account = Account.getInstance();
    private Category category = Category.getInstance();
    private Filter filter = Filter.getInstance();


    private List<Product> filteredProducts = new ArrayList<>();

    @FXML
    public void backMain() throws IOException {
        SwitchScreen.changeScreen("views/landingPage.fxml");
    }

    @FXML
    public void initialize() throws SQLException {
        lwCategoryItems.getItems().clear();
        File file = new File("src/main/resources/com/example/swapper/views/images/" + category.getNameOfCategory() + ".png");
        Image image = new Image(file.toURI().toString());
        categories.setImage(image);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbSort.setText(resourceBundle.getString("sortByDate"));
        if (account.getCurrentUser() == null) {
            btnLogin.setText(resourceBundle.getString("login"));
            btnLogin.setVisible(true);
            ivAvatar.setVisible(false);
        } else {
            btnAddProduct.setText(resourceBundle.getString("addProduct"));
            btnAddProduct.setVisible(true);
        }

        //user searched by searchbar
        if (filter.getSearchInput() != null) {
            ResultSet result = ProductDbServices.getProductsByName(filter.getSearchInput());
            UIHelper.mapResultSetToProducts(result, filteredProducts, lwCategoryItems);
            System.out.println(filteredProducts);
        } //search by filter
        else if (filter.getCategory() != null) {
            ResultSet filteredProductsList = ProductDbServices.getFilteredProducts(filter.getDateFrom(), filter.getDateTo(), filter.getCategory());
            UIHelper.mapResultSetToProducts(filteredProductsList, filteredProducts, lwCategoryItems);
            filter.resetFilterValues();
        }
        //else - search by categories
        else {
            ResultSet productsByCategory = ProductDbServices.getProductsByCategory(category.getNameOfCategory());
            UIHelper.mapResultSetToProducts(productsByCategory, filteredProducts, lwCategoryItems);
        }

    }

    @FXML
    public void searchForProduct() throws IOException {
        filter.setSearchInput(tfSearch.getText());
        category.setNameOfCategory("");
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
        account.setCurrentProduct(filteredProducts.get(offersIndex));
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

    public void redirectToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void setDateASC() {
        filteredProducts.sort(new ProductComparator());
        lwCategoryItems.getItems().clear();
        filteredProducts.forEach(e -> lwCategoryItems.getItems().add(e.toString()));
    }

    public void setDateDESC() {
        filteredProducts.sort(new ProductComparator().reversed());
        lwCategoryItems.getItems().clear();
        filteredProducts.forEach(e -> lwCategoryItems.getItems().add(e.toString()));
    }

}
