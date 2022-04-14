package models;

public class Category {
    private static Category instance = null;
    private static String name = "";

    public static Category getInstance() {
        if (instance == null) {
            instance = new Category();
        }
        return instance;
    }

    public static String getNameOfCategory() {
        return name;
    }

    public static void setNameOfCategory(String newName) {
        name = newName;
    }


}
