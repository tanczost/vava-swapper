package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

public class SelectPropositionController extends Subject {
    @FXML
    public Button btnTrade;
    @FXML
    public ImageView ivProduct;
    @FXML
    public Label lbProduct;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbTopBadge;
    @FXML
    public Label lbUserProductLabel;
    @FXML
    public ComboBox cbUserItems;
    @FXML
    public Button btnBack;
    @FXML
    public Label lbTop;
    private Account account = Account.getInstance();

    @FXML
    public void initialize() throws Exception {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Select proposition page loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
        btnBack.setText(resourceBundle.getString("back"));

        if (!account.getCurrentProduct().isTopped()) {
            lbTop.setVisible(false);
        }


        lbProduct.setText(account.getCurrentProduct().getName());
        lbDescription.setText(account.getCurrentProduct().getDescription());

        InputStream is = FileHandler.getFile(account.getCurrentProduct().getImgId());
        ivProduct.setImage(new Image(is));

        for (Product product : account.getProductsOfLoggedUser()) {
            cbUserItems.getItems().add(product.getName());
        }

    }


    public void trade() throws IOException, SQLException {
        int index = cbUserItems.getSelectionModel().getSelectedIndex();
        Product selected = account.getProductsOfLoggedUser().get(index);
        ProductDbServices.insertOfferDb(account.getCurrentProduct().getId(), selected.getId());
        account.setCurrentOffer(null);
        account.setCurrentProduct(null);

        this.notifyObserver("Trade done.", Observer.LEVEL.info);
        SwitchScreen.changeScreen("views/LandingPage.fxml");

    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/CategoryPage.fxml");
    }
}