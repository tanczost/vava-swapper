package com.example.swapper;

import javafx.fxml.FXML;
import models.Product;
import service.FileHandler;
import service.db.ProductDbServices;

import java.io.IOException;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.image.BufferedImage;


public class PropositionController {

    @FXML
    public void initialize() throws SQLException, IOException {
        ResultSet result = ProductDbServices.getProductById(3);
        result.next();
        Product currentProduct = new Product(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getBoolean(4),
                result.getInt(5)
        );

        System.out.println(currentProduct.getImgId());

        byte[] img = FileHandler.getFile(currentProduct.getImgId());
        System.out.println(img.toString());
        BufferedImage bufferedImg = FileHandler.createImageFromByteStream(img);

        System.out.println(bufferedImg);
        //TODO: load it into fields

    }
}
