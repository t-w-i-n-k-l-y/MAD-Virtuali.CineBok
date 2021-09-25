package com.virtuali.cinebok.model;

import java.io.Serializable;

public class SnackBeverage implements Serializable {
    //image,
    String sbName, sbSize, sbAvailability, sburl;
    double sbPrice;

    public SnackBeverage() {
    }

    public SnackBeverage(String url, String sbName, String sbSize, double sbPrice, String sbAvailability) {
        this.sburl = url;
        this.sbName = sbName;
        this.sbSize = sbSize;
        this.sbPrice = sbPrice;
        this.sbAvailability = sbAvailability;
    }

    public String getSbUrl() {
        return sburl;
    }

    public void setSbUrl(String url) {
        this.sburl = url;
    }

    public String getSbName() {
        return sbName;
    }

    public void setSbName(String sbName) {
        this.sbName = sbName;
    }

    public String getSbSize() {
        return sbSize;
    }

    public void setSbSize(String sbSize) {
        this.sbSize = sbSize;
    }

    //---------------------------------------------------------------------------------
    public String getSbPrice() {
        return String.valueOf(sbPrice);
    }

    public void setSbPrice(String sbPrice) {
        this.sbPrice = Double.parseDouble(sbPrice);
    }
    //---------------------------------------------------------------------------------



    public String getSbAvailability() {
        return sbAvailability;
    }

    public void setSbAvailability(String sbAvailability) {
        this.sbAvailability = sbAvailability;
    }
}
