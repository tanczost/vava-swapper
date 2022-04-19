package service;

import javafx.scene.control.ListView;
import models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UIHelper {
    public static void mapResultSetToProducts(ResultSet result, List<Product> allProducts, ListView<String> lvAllProducts) throws SQLException {
        allProducts.clear();
        if (result == null) {
            return;
        }
        while (result.next()) {
            allProducts.add(new Product(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBoolean(4),
                    result.getInt(5),
                    result.getTimestamp(8)
            ));
        }

        allProducts.forEach(e -> {
            System.out.println(e.toString());
            lvAllProducts.getItems().add(e.toString());
        });
    }
}
