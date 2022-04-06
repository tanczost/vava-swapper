package models;

public class Admin {
    private static Product selectedProduct;

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public static void setSelectedProduct(Product selectedProduct) {
        Admin.selectedProduct = selectedProduct;
    }
}
