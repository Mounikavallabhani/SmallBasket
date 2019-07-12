package com.arkainfoteck.smallbasket.Activity;

import android.icu.text.SymbolTable;

public class Model  {
    String id;
    String r_id;
    String title_name;
    String mrp_cost;
    String rs_cost;
    String image;
    String product_type_one;
    String restaurant_name;
    String aprice12;

    public Model(String id, String r_id, String title_name, String mrp_cost, String rs_cost, String image, String product_type_one, String restaurant_name, String aprice12) {
        this.id = id;
        this.r_id = r_id;
        this.title_name = title_name;
        this.mrp_cost = mrp_cost;
        this.rs_cost = rs_cost;
        this.image = image;
        this.product_type_one = product_type_one;
        this.restaurant_name = restaurant_name;
        this.aprice12 = aprice12;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getMrp_cost() {
        return mrp_cost;
    }

    public void setMrp_cost(String mrp_cost) {
        this.mrp_cost = mrp_cost;
    }

    public String getRs_cost() {
        return rs_cost;
    }

    public void setRs_cost(String rs_cost) {
        this.rs_cost = rs_cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_type_one() {
        return product_type_one;
    }

    public void setProduct_type_one(String product_type_one) {
        this.product_type_one = product_type_one;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getAprice12() {
        return aprice12;
    }

    public void setAprice12(String aprice12) {
        this.aprice12 = aprice12;
    }
}
