package models;

public class Admin {
    private static Admin instance = null;
    private static Product selectedProduct;

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public static void setSelectedProduct(Product selectedProduct) {
        Admin.selectedProduct = selectedProduct;
    }
}
