package models;

public class Product {
    private int id;
    private String name;
    private String description;
    private boolean topped;
    //TODO add image also


    public Product(int id, String name, String description, boolean topped) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topped = topped;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTopped() {
        return topped;
    }
}
