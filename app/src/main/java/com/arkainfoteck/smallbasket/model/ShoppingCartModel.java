package com.arkainfoteck.smallbasket.model;

public class ShoppingCartModel {

    String product_id;
    String restaurant_id;
    String product_name;
    String rs_cost;
    String aprice12;
    String image;
    String product_type_one;
    String restaurant_name;
    String price;
    String quantity;

   /* String product_id;
    String restaurant_id;
    String product_name;
    String image;
    String quantity;
    String price;
*/

    public ShoppingCartModel(String product_id, String restaurant_id, String product_name, String image,  String quantity, String price) {
        this.product_id = product_id;
        this.restaurant_id = restaurant_id;
        this.product_name = product_name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public ShoppingCartModel(String product_id, String restaurant_id, String product_name, String rs_cost, String aprice12, String image, String product_type_one, String restaurant_name, String price, String quantity) {
        this.product_id = product_id;
        this.restaurant_id = restaurant_id;
        this.product_name = product_name;
        this.rs_cost = rs_cost;
        this.aprice12 = aprice12;
        this.image = image;
        this.product_type_one = product_type_one;
        this.restaurant_name = restaurant_name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRs_cost() {
        return rs_cost;
    }

    public void setRs_cost(String rs_cost) {
        this.rs_cost = rs_cost;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
