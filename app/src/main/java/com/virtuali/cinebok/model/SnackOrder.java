package com.virtuali.cinebok.model;

import java.util.ArrayList;

public class SnackOrder {
    ArrayList<Integer> al = new ArrayList<>();

    public SnackOrder() {
    }

    public ArrayList<Integer> getAl() {
        return al;
    }

    public void setAl(ArrayList<Integer> al) {
        this.al = al;
    }

    public String getTot(){
        int sum = 0;
        for(int i = 0; i < this.al.size(); i++){
            sum += this.al.get(i);
        }

        return String.valueOf(sum);
    }
}
