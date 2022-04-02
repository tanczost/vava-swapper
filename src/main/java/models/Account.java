package models;

import service.db.ProductDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {
    public static User currentUser = null;
    public static ArrayList<Product> productsOfLoggedUser = new ArrayList<>();
    public static Product currentProduct = null;

    public static void createAccount(int id, String nick, String firstName, String lastName, String email, String town, String street, String school) {
        if (currentUser == null) {
            currentUser = new User(id, nick, firstName, lastName, email, town, street, school);
        } else {
            System.out.println("User already logged in");
        }
    }

    public static String checkLogin() {
        if (currentUser == null) {
            return "Nobody is logged in";
        } else {
            return currentUser.toString();
        }
    }

    public static int getLoggedUserId() {
        if (currentUser == null) return 1;  //return -1;

        return currentUser.getId();
    }


    public static ArrayList<Product> getProductsOfLoggedUser() throws SQLException {
        loadProducts();
        return productsOfLoggedUser;
    }

    public static void addProductsOfLoggedUser(Product newProduct) {
        Account.productsOfLoggedUser.add(newProduct);
    }

    public static void loadProducts() throws SQLException {
        ResultSet products = ProductDbServices.getUsersProposals(getLoggedUserId());
        productsOfLoggedUser.clear();

        if(products == null){
            return;
        }

        while (products.next()) {
            productsOfLoggedUser.add(new Product(
                    products.getInt(1),
                    products.getString(2),
                    products.getString(3),
                    products.getBoolean(4),
                    0
            ));
        }
        System.out.println("Products are successfully loaded into account");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Product getCurrentProduct() {
        return currentProduct;
    }

    public static void setCurrentProduct(Product currentProduct) {
        Account.currentProduct = currentProduct;
    }
}
