package controllers;

import com.example.swapper.SwapperApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Account;
import models.Product;
import observer.Observer;
import observer.Subject;
import services.db.ProductDbServices;
import services.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProposalPageController extends Subject {
    @FXML
    public Button btnOffers;
    @FXML
    public Button btnProposals;
    @FXML
    public Button btnUpdateProduct;
    @FXML
    public ListView lvUserItems;
    @FXML
    public Button btnLogout;
    @FXML
    private ComboBox cbUserItems;
    @FXML
    private Label lbName;
    @FXML
    private Label lbItems;
    @FXML
    private Button btnEditInfo;
    @FXML
    private Button btnMainPage;
    @FXML
    private Button btnToggleLanguage;
    @FXML
    public Button addProduct;
    private List<Product> offersForProduct = new ArrayList<>();
    private Account account = Account.getInstance();

    @FXML
    public void initialize() throws SQLException {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Page with proposals loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        lbItems.setText(resourceBundle.getString("yourItems"));
        btnEditInfo.setText(resourceBundle.getString("editInfo"));
        btnMainPage.setText(resourceBundle.getString("mainPage"));
        btnToggleLanguage.setText(resourceBundle.getString("toggleLanguage"));
        btnOffers.setText(resourceBundle.getString("myOffers"));
        btnProposals.setText(resourceBundle.getString("myProposals"));
        addProduct.setText(resourceBundle.getString("addProduct"));
        btnUpdateProduct.setText(resourceBundle.getString("updateProduct"));
        btnLogout.setText(resourceBundle.getString("logout"));


        lbName.setText(account.getCurrentUser().getNickName().toUpperCase(Locale.ROOT));
        for (Product product : account.getProductsOfLoggedUser()) {
            cbUserItems.getItems().add(product.getName());
        }
    }

    public void toggleLanguage() throws IOException {
        boolean hasEnglishSelected = account.getCurrentUser().hasEnglishLanguageSelected();
        if (hasEnglishSelected) {
            this.notifyObserver("Changed to slovak language.", Observer.LEVEL.info);
            Locale.setDefault(new Locale("sk", "SK"));
        } else {
            this.notifyObserver("Changed to english language.", Observer.LEVEL.info);
            Locale.setDefault(new Locale("en", "EN"));
        }
        account.getCurrentUser().setHasEnglishLanguageSelected(!hasEnglishSelected);
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/AddProductModal.fxml");
    }

    public void redirectToMainPage() throws IOException {
        SwitchScreen.changeScreen("views/LandingPage.fxml");
    }

    public void redirectToEditPersonalInfo() throws IOException {
        SwitchScreen.changeScreen("views/EditPersonalInfoModal.fxml");
    }

    public void redirectToUpdateProduct() throws IOException {
        if (cbUserItems.getSelectionModel().isEmpty()) {
            return;
        }
        SwitchScreen.changeScreen("views/ModifyProductModal.fxml");
    }

    public void redirectToOfferPage() throws IOException {
        SwitchScreen.changeScreen("views/OfferPage.fxml");
    }

    public void offerSelected() throws IOException {
        if (lvUserItems.getSelectionModel().isEmpty()) {
            return;
        }

        int offersIndex = lvUserItems.getSelectionModel().getSelectedIndex();
        account.setCurrentOffer(offersForProduct.get(offersIndex));

        SwitchScreen.changeScreen("views/PropositionModal.fxml");
    }

    public void productSelected() throws SQLException {
        for (Product product : account.getProductsOfLoggedUser()) {
            if (product.getName().equals(cbUserItems.getValue().toString())) {
                account.setCurrentProduct(product);
                break;
            }
        }

        ResultSet offers = ProductDbServices.getOffersForProduct(account.getCurrentProduct().getId());

        lvUserItems.getItems().clear();
        if (offers == null) {
            return;
        }

        offersForProduct.clear();


        while (offers.next()) {
            offersForProduct.add(new Product(
                    offers.getInt(1),
                    offers.getString(2),
                    offers.getString(3),
                    offers.getBoolean(4),
                    offers.getInt(5),
                    offers.getTimestamp(7)
            ));
        }

        offersForProduct.forEach(e -> {
            lvUserItems.getItems().add(e.toString());
        });
    }

    public void logout() throws IOException {
        account.logout();
        this.notifyObserver("User logged out.", Observer.LEVEL.info);
        SwitchScreen.changeScreen("views/LoginPage.fxml");
    }
}
