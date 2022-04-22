package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Admin;
import models.Product;
import observer.Observer;
import observer.Subject;
import service.common.FileHandler;
import service.db.ProductDbServices;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDeleteController extends Subject {
    @FXML
    public Label lbProductName;
    @FXML
    public Label lbDescription;
    @FXML
    public Label lbCategory;
    @FXML
    public Button btnBack;
    @FXML
    public Button btnDelete;
    @FXML
    public ImageView ivImg;

    private Product currentProduct;

    @FXML
    public void initialize() throws Exception {
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Delete page for admins loaded.", Observer.LEVEL.info);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnBack.setText(resourceBundle.getString("back"));
        btnDelete.setText(resourceBundle.getString("delete"));
        ResultSet result = ProductDbServices.getProductById(Admin.getInstance().getSelectedProduct().getId());


        while (result.next()) {
            currentProduct = new Product(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBoolean(4),
                    result.getInt(5),
                    result.getTimestamp(8)
            );
            lbCategory.setText(result.getString(7));
        }

        InputStream is = FileHandler.getFile(currentProduct.getImgId());
        ivImg.setImage(new Image(is));

        lbProductName.setText(currentProduct.getName());
        lbDescription.setText(currentProduct.getDescription());
    }

    public void deleteProduct() throws IOException, SQLException {
        this.notifyObserver("Product was deleted.", Observer.LEVEL.info);
        ProductDbServices.deleteProduct(currentProduct.getId());
        SwitchScreen.changeScreen("views/AdminPage.fxml");

    }

    public void redirectBack() throws IOException {
        SwitchScreen.changeScreen("views/AdminPage.fxml");
    }
}