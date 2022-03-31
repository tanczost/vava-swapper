package com.example.swapper;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Category;
import service.navigation.SwitchScreen;

import java.io.IOException;


public class LandingPageController {
    @FXML
    private Button login;
    @FXML
    private Button addproduct;


    @FXML
    public void changeScenario(MouseEvent event) throws IOException {
        ImageView Item = (ImageView) event.getSource();
        Category.getInstance().setNameOfCategory(Item.getId());
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }


    @FXML
    public void initialize() {
        if (Account.getCurrentUser() == null) {
            login.setVisible(true);
        } else {
            addproduct.setVisible(true);
        }
    }

    @FXML
    public void loadAddProductPage() throws IOException {
        SwitchScreen.changeScreen("views/addProduct.fxml");
    }

    public void navigateToProposalPage(MouseEvent mouseEvent) throws IOException {
        SwitchScreen.changeScreen("views/ProposalPage.fxml");
    }
}
