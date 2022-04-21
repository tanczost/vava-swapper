package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Account;
import observer.Observer;
import observer.Subject;
import service.common.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PropositionController extends Subject {
    @FXML
    public ImageView ivProduct;
    @FXML
    public Button btnTrade;
    @FXML
    public Label lbProduct;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbUserProductLabel;
    @FXML
    public Label lbTopBadge;
    @FXML
    public Label lbUserProduct;
    @FXML
    public Button btnDecline;
    @FXML
    public Button btnBack;
    @FXML
    public Label lbTop;
    private Account account = Account.getInstance();


    @FXML
    public void initialize() throws Exception {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Page for proposition loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProduct.setText(account.getCurrentProduct().getName());
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
        btnDecline.setText(resourceBundle.getString("decline"));
        btnBack.setText(resourceBundle.getString("back"));

        if (!account.getCurrentOffer().isTopped()) {
            lbTop.setVisible(false);
        }

        lbProduct.setText(account.getCurrentOffer().getName());
        lbDescription.setText(account.getCurrentOffer().getDescription());

        InputStream is = FileHandler.getFile(account.getCurrentOffer().getImgId());
        ivProduct.setImage(new Image(is));

    }

    public void trade() throws SQLException, IOException {
        ProductDbServices.tradeProduct(account.getCurrentProduct().getId(), account.getCurrentOffer().getId());
        account.setCurrentOffer(null);
        account.setCurrentProduct(null);
        this.notifyObserver("Trade finished.", Observer.LEVEL.info);
        SwitchScreen.changeScreen("views/LandingPage.fxml");
    }

    public void declineProposal() throws SQLException, IOException {
        ProductDbServices.deleteProposal(account.getCurrentProduct().getId(), account.getCurrentOffer().getId());
        this.notifyObserver("Proposal declined.", Observer.LEVEL.info);
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
