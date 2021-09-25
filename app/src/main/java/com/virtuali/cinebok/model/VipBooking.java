package com.virtuali.cinebok.model;

public class VipBooking {

    String CusName, hName, mName;
    double tPrice, tTotal, sTotal, Total;
    int noTickets;

    public VipBooking() {
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String gettPrice() {
        return String.valueOf(tPrice);
    }

    public void settPrice(String tPrice) {
        this.tPrice = Double.parseDouble(tPrice);
    }

    public String gettTotal() {
        return String.valueOf(tTotal);
    }

    public void settTotal(String tTotal) {
        this.tTotal = Double.parseDouble(tTotal);
    }

    public String getNoTickets() {
        return String.valueOf(noTickets);
    }

    public void setNoTickets(String noTickets) {
        this.noTickets = Integer.parseInt(noTickets);
    }

    public String getsTotal() {
        return String.valueOf(sTotal);
    }

    public void setsTotal(String sTotal) {
        this.sTotal = Double.parseDouble(sTotal);
    }

    public String getTotal() {
        return String.valueOf(Total);
    }

    public void setTotal(String total) {
        Total = Double.parseDouble(total);
    }

    public String getTotalForTickets () {
        this.tTotal = this.noTickets * this.tPrice;

        return String.valueOf(this.tTotal);
    }

    public String getTotal_st (){
        return String.valueOf(tTotal + sTotal);
    }


}

