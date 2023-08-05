package com.example.myapplication.models;

public class ItemCatalog {
    int sourceImg;
    String name;
    String description;
    float price;
    String brand;

    public ItemCatalog(int sourceImg, String name, String description, float price, String brand){
        this.sourceImg = sourceImg;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    public int getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(int sourceImg) {
        this.sourceImg = sourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ItemCatalog{" +
                "sourceImg='" + sourceImg + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
