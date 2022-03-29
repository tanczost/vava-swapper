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

        System.out.println(currentProduct.getImgId());

        byte[] img = FileHandler.getFile(6);
        //int id = FileHandler.uploadFile(FileHandler.readImageToByteStream("C:\\Users\\madre\\Documents\\GitHub\\vava-swapper\\src\\main\\resources\\test.jpg"));
        //System.out.println(id);

        System.out.println(Arrays.toString(img));
        //TODO: load it into fields
    }
}
