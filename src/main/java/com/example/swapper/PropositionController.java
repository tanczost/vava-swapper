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
import java.util.Arrays;


public class PropositionController {

    @FXML
    public void initialize() throws Exception {
        ResultSet result = ProductDbServices.getProductById(3);
        result.next();
        Product currentProduct = new Product(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getBoolean(4),
                result.getInt(5)
        );


        //byte[] img = FileHandler.getFile(6);
        //BufferedImage photo = FileHandler.createImageFromByteStream(img);

        //TODO: load it into fields
    }
}
