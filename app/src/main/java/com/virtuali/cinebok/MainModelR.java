package com.virtuali.cinebok;

public class MainModelR {

    String name,director ,description , murl;


    MainModelR(){

    }

    public MainModelR(String name, String director, String description, String murl) {
        this.name = name;
        this.director = director;
        this.description = description;
        this.murl = murl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }
}
