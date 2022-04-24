package models;

import com.example.swapper.SwapperApplication;
import observer.Observer;
import observer.Subject;
import services.db.ProductDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Account extends Subject {
    private static Account instance = null;
    private  User currentUser = null;
    private  ArrayList<Product> productsOfLoggedUser = new ArrayList<>();
    private  Product currentProduct = null;
    private  Product currentOffer = null;

    private Account(){
        this.attach(SwapperApplication.getLogManager());
    }

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public  void createAccount(int id, String nick, String firstName, String lastName, String email, String town, String street, String school) {
        if (currentUser == null) {
            currentUser = new User(id, nick, firstName, lastName, email, town, street, school);
        } else {
            SwapperApplication.getLogManager().update("Cant authenticate (log in) an allready authenticated user.", Observer.LEVEL.warning);
        }
    }

    public  void logout() {
        currentUser = null;
        productsOfLoggedUser.clear();
        currentProduct = null;
        currentOffer = null;
    }

    public  boolean checkLogin() {
        return currentUser != null;
    }

    public  int getLoggedUserId() {
        if (currentUser == null) return 1;  //return -1;

        return currentUser.getId();
    }

    public  void setCurrentUserDetails(String nickName, String firstName, String lastName, String email, String town, String street, String school) {
        currentUser.setNickName(nickName);
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setTown(town);
        currentUser.setStreet(street);
        currentUser.setSchool(school);
    }


    public  ArrayList<Product> getProductsOfLoggedUser() throws SQLException {
        loadProducts();
        return productsOfLoggedUser;
    }

    public  void loadProducts() throws SQLException {
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
        SwapperApplication.getLogManager().update("Products are successfully loaded into account.", Observer.LEVEL.info);
    }

    public  User getCurrentUser() {
        return currentUser;
    }

    public  Product getCurrentProduct() {
        return currentProduct;
    }

    public  void setCurrentProduct(Product currentProduct) {
        instance.currentProduct = currentProduct;
    }

    public  Product getCurrentOffer() {
        return currentOffer;
    }

    public  void setCurrentOffer(Product currentOffer) {
        instance.currentOffer = currentOffer;
    }

}
