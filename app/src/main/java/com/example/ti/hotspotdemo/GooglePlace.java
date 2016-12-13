package com.example.ti.hotspotdemo;

/**
 * Created by ti on 17/05/2016.
 */
public class GooglePlace {
    private String name;
    private String place;
    private String category;
    private String rating;
    private String open;

    public GooglePlace() {
        this.name = "";
        this.rating = "";
        this.open = "";
        this.setCategory("");
        this.place="";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setOpenNow(String open) {
        this.open = open;
    }

    public String getOpenNow() {
        return open;
    }

    public String getPlace() {
        return place;
    }


    public void setPlace(String place) {
        this.place = place;
    }
}

