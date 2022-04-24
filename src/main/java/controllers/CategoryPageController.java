package controllers;

import com.example.swapper.SwapperApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Filter;
import models.Product;
import observer.Observer;
import observer.Subject;
import services.common.ProductComparator;
import services.common.UIHelper;
import services.db.ProductDbServices;
import services.navigation.SwitchScreen;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryPageController extends Subject {
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
    private Filter filter = Filter.getInstance();


    private List<Product> filteredProducts = new ArrayList<>();

    @FXML
    public void backMain() throws IOException {
        SwitchScreen.changeScreen("views/LandingPage.fxml");
    }

    @FXML
    public void changeHighlight(MouseEvent event){
        ImageView Item = (ImageView) event.getSource();
        Image newImage = new Image(String.valueOf(SwapperApplication.class.getResource("views/images/"+Item.getId()+"_highlight.png")));
        Image oldImage = new Image(String.valueOf(SwapperApplication.class.getResource("views/images/"+Item.getId()+".png")));
        Item.setImage(newImage);
        EventHandler<MouseEvent> highlight = e -> Item.setImage(oldImage);
        Item.setOnMouseExited(highlight);
    }

    @FXML
    public void initialize() throws SQLException {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Categories page loaded.", Observer.LEVEL.info);

        lwCategoryItems.getItems().clear();
        File file = new File("src/main/resources/controllers/views/images/" + filter.getCategory() + ".png");
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
        } //search by category
        else if (filter.getCategory() != null) {
            if (filter.getDateFrom() != null){
                ResultSet filteredProductsList = ProductDbServices.getFilteredProducts(filter.getDateFrom(), filter.getDateTo(), filter.getCategory(),filter.getIsTop());
                UIHelper.mapResultSetToProducts(filteredProductsList, filteredProducts, lwCategoryItems);
                filter.resetFilterValues();
            } else {
                ResultSet productsByCategory = ProductDbServices.getProductsByCategory(filter.getCategory());
                UIHelper.mapResultSetToProducts(productsByCategory, filteredProducts, lwCategoryItems);
            }
        }
        //else - search by TOP
        else if (filter.getIsTop()) {
            ResultSet filteredProductsList = ProductDbServices.getFilteredProducts(null, null, null,filter.getIsTop());
            UIHelper.mapResultSetToProducts(filteredProductsList, filteredProducts, lwCategoryItems);
            filter.resetFilterValues();
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

    public void offerSelected() throws IOException {
        if (lwCategoryItems.getSelectionModel().isEmpty()) {
            return;
        }
        int offersIndex = lwCategoryItems.getSelectionModel().getSelectedIndex();
        account.setCurrentProduct(filteredProducts.get(offersIndex));
        SwitchScreen.changeScreen("views/SelectPropositionModal.fxml");
    }

    public void redirectToFilterPage() throws IOException {
        SwitchScreen.changeScreen("views/FilterModal.fxml");
    }

    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/AddProductModal.fxml");
    }

    public void redirectToLogin() throws IOException {
        SwitchScreen.changeScreen("views/LoginPage.fxml");
    }

    public void redirectToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void setDateASC() {
        filteredProducts.sort(new ProductComparator());
        lwCategoryItems.getItems().clear();
        filteredProducts.forEach(e -> lwCategoryItems.getItems().add(e.toString()));
        this.notifyObserver("Sorting set to ASC order.", Observer.LEVEL.info);
    }

    public void setDateDESC() {
        filteredProducts.sort(new ProductComparator().reversed());
        lwCategoryItems.getItems().clear();
        filteredProducts.forEach(e -> lwCategoryItems.getItems().add(e.toString()));
        this.notifyObserver("Sorting set to DESC order.", Observer.LEVEL.info);
    }

}
