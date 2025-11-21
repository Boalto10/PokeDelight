package com.pokedelight.model;

public class PokemonItem {
    private int id;
    private String name;
    private String ptype;
    private double price;
    private String description;
    private String imagePath;

    public PokemonItem() {}

    public PokemonItem(int id, String name, String ptype, double price, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.ptype = ptype;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPtype() { return ptype; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPtype(String ptype) { this.ptype = ptype; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
