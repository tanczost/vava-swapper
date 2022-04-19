package models;

import service.db.ProductDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Account{
    private static Account instance = null;
    private static User currentUser = null;
    private static ArrayList<Product> productsOfLoggedUser = new ArrayList<>();
    private static Product currentProduct = null;
    private static Product currentOffer = null;

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public static void createAccount(int id, String nick, String firstName, String lastName, String email, String town, String street, String school) {
        if (currentUser == null) {
            currentUser = new User(id, nick, firstName, lastName, email, town, street, school);
        } else {
            System.out.println("User already logged in");
        }
    }

    public static void logout() {
        currentUser = null;
        productsOfLoggedUser.clear();
        currentProduct = null;
        currentOffer = null;
    }

    public static boolean checkLogin() {
        return currentUser != null;
    }

    public static int getLoggedUserId() {
        if (currentUser == null) return 1;  //return -1;

        return currentUser.getId();
    }

    public static void setCurrentUserDetails(String nickName, String firstName, String lastName, String email, String town, String street, String school) {
        currentUser.setNickName(nickName);
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setTown(town);
        currentUser.setStreet(street);
        currentUser.setSchool(school);
    }


    public static ArrayList<Product> getProductsOfLoggedUser() throws SQLException {
        loadProducts();
        return productsOfLoggedUser;
    }

    public static void loadProducts() throws SQLException {
        ResultSet products = ProductDbServices.getUsersProposals(getLoggedUserId());
        productsOfLoggedUser.clear();

        if (products == null) {
            return;
        }

        while (products.next()) {
            productsOfLoggedUser.add(new Product(
                    products.getInt(1),
                    products.getString(2),
                    products.getString(3),
                    products.getBoolean(4),
                    products.getInt(5),
                    products.getTimestamp(8)
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

    public static Product getCurrentOffer() {
        return currentOffer;
    }

    public static void setCurrentOffer(Product currentOffer) {
        Account.currentOffer = currentOffer;
    }

}
