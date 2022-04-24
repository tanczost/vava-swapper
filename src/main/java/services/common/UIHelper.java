package services.common;

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

        allProducts.forEach(e -> {lvAllProducts.getItems().add(e.toString());});
    }

    public static void mapResultSetToProducts(ResultSet result, List<Product> myProducts,List<Product> othersProducts, ListView<String> lvAllProducts) throws SQLException {
        myProducts.clear();
        othersProducts.clear();

        if (result == null) {
            return;
        }
        while (result.next()) {

            myProducts.add(new Product(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBoolean(4),
                    result.getInt(5),
                    result.getTimestamp(8)
            ));

            othersProducts.add(new Product(
                    result.getInt(10),
                    result.getString(11),
                    result.getString(12),
                    result.getBoolean(13),
                    result.getInt(14),
                    result.getTimestamp(17)
            ));
        }

        othersProducts.forEach(e -> {
            lvAllProducts.getItems().add(e.toString());
        });
    }
}
