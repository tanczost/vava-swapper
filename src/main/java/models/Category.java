package models;

public class Category {
    private static Category single_instance;
    public String name = "";

    private Category() {

    }
    
    public static Category getInstance() {
        if (single_instance == null) single_instance = new Category();

        return single_instance;
    }

    public String getNameOfCategory() {
        return this.name;
    }

    public void setNameOfCategory(String newName) {
        this.name = newName;
    }


}
