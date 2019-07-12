package com.arkainfoteck.smallbasket.model;

public class ProductNewModel {
    int image;
    String name;
    String kgs;
    String cost;

    public ProductNewModel(int image, String name, String kgs, String cost) {
        this.image = image;
        this.name = name;
        this.kgs = kgs;
        this.cost = cost;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKgs() {
        return kgs;
    }

    public void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
