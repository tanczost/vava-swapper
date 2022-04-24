package models;

public class Admin {
    private static Admin instance = null;
    private Product selectedProduct;

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        instance.selectedProduct = selectedProduct;
    }
}
