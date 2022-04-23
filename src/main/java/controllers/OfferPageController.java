package controllers;

import com.example.swapper.SwapperApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Account;
import models.Product;
import observer.Observer;
import observer.Subject;
import services.common.UIHelper;
import services.db.ProductDbServices;
import services.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OfferPageController extends Subject {
    @FXML
    public Button addProduct;
    @FXML
    public Button btnMainPage;
    @FXML
    public Button btnOffers;
    @FXML
    public Button btnProposals;
    @FXML
    public ListView lvOffers;
    private List<Product> othersProducts = new ArrayList<>();
    private List<Product> myProducts = new ArrayList<>();

    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/AddProductModal.fxml");
    }

    @FXML
    public void initialize() throws SQLException {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Offers page loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnOffers.setText(resourceBundle.getString("myOffers"));
        btnProposals.setText(resourceBundle.getString("myProposals"));
        addProduct.setText(resourceBundle.getString("addProduct"));
        allOffers();
    }

    public void allOffers() throws SQLException {
        ResultSet result = ProductDbServices.getMyOffers();
        UIHelper.mapResultSetToProducts(result,myProducts, othersProducts, lvOffers);
    }

    public void offerSelected() throws IOException {
        if (lvOffers.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lvOffers.getSelectionModel().getSelectedIndex();
        System.out.println(othersProducts.get(offersIndex).toString());
        Account.setCurrentProduct(myProducts.get(offersIndex));
        Account.setCurrentOffer(othersProducts.get(offersIndex));

        SwitchScreen.changeScreen("views/OfferDetail.fxml");
    }

    public void redirectToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/LandingPage.fxml");
    }

    public void redirectToProposalPage() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
