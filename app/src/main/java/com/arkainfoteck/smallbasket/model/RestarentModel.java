package com.arkainfoteck.smallbasket.model;

public class RestarentModel  {
    String restarentimage;
    String restarentname;
    String restarenttype;
    String reviews;
    String id;
    String product_type;

    public RestarentModel(String restarentimage, String restarentname, String restarenttype, String reviews, String id, String product_type) {
        this.restarentimage = restarentimage;
        this.restarentname = restarentname;
        this.restarenttype = restarenttype;
        this.reviews = reviews;
        this.id = id;
        this.product_type = product_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getRestarentimage() {
        return restarentimage;
    }

    public void setRestarentimage(String restarentimage) {
        this.restarentimage = restarentimage;
    }

    public String getRestarentname() {
        return restarentname;
    }

    public void setRestarentname(String restarentname) {
        this.restarentname = restarentname;
    }

    public String getRestarenttype() {
        return restarenttype;
    }

    public void setRestarenttype(String restarenttype) {
        this.restarenttype = restarenttype;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
