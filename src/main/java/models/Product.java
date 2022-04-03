package models;

public class Product {
    private int id;
    private String name;
    private String description;
    private boolean topped;
    private int imgId;


    public Product(int id, String name, String description, boolean topped, int imgId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topped = topped;
        this.imgId = imgId;
    }

    public int getId() {
        return id;
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

    public int getImgId() {
        return imgId;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\t\tDescription: " + description;
    }
}
