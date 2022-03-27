package com.example.swapper;

import javafx.fxml.FXML;
import models.Product;
import service.db.ProductDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropositionController {

    @FXML
    public void initialize() throws SQLException {
        ResultSet result = ProductDbServices.getProductById(1);
        result.next();
        Product currentProduct = new Product(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getBoolean(4)
        );

        //TODO: load it into fields

    }
}
