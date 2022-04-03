package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;


public class PropositionController {
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
    public void initialize() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnTrade.setText(resourceBundle.getString("trade"));
        lbUserProductLabel.setText(resourceBundle.getString("yourItem"));
//        ResultSet result = ProductDbServices.getProductById(3);
//        result.next();
//        Product currentProduct = new Product(
//                result.getInt(1),
//                result.getString(2),
//                result.getString(3),
//                result.getBoolean(4),
//                result.getInt(5)
//        );


        //TODO template to display img from db
//        InputStream is = FileHandler.getFile(currentProduct.getImgId);
//        Image img = new Image(is);
//        imgView.setImage(img);

    }
}
