package com.arkainfoteck.smallbasket.model;

public class OrderHistoryModel {
    String ordernumber;
    String orederdate;
    String orderstatus;
    String otderamount;

    public OrderHistoryModel(String ordernumber, String orederdate, String orderstatus, String otderamount) {
        this.ordernumber = ordernumber;
        this.orederdate = orederdate;
        this.orderstatus = orderstatus;
        this.otderamount = otderamount;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getOrederdate() {
        return orederdate;
    }

    public void setOrederdate(String orederdate) {
        this.orederdate = orederdate;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOtderamount() {
        return otderamount;
    }

    public void setOtderamount(String otderamount) {
        this.otderamount = otderamount;
    }
}
