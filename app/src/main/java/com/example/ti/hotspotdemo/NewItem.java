package com.example.ti.hotspotdemo;

/**
 * Created by ti on 19/05/2016.
 */
public class NewItem {
    private String name ;
    private String Description;
    private String url;

    public NewItem(){}

    public NewItem(String name, String Description,String url){
        this.name = name;
        this.Description = Description;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
