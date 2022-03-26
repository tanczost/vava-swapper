package models;

public class Singleton {
    private static Singleton single_instance;
    public String nameOfProduct = "";

    private Singleton()
    {

    }


    public static Singleton getInstance()
    {
        if (single_instance == null) single_instance = new Singleton();

        return single_instance;
    }

    public String getNameOfProduct() {
        return this.nameOfProduct;
    }

    public void setNameOfProduct(String newName) {
       this.nameOfProduct = newName;
    }

}
