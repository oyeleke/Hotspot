package com.example.ti.hotspotdemo;

/**
 * Created by ti on 19/05/2016.
 */
public class PlaceDetails {

    private String name;
    private String formatted_address;
    private String formatted_phone_number;
    private String international_phone_number;

    public  PlaceDetails(){}


    public PlaceDetails (String name, String formatted_address, String formatted_phone_number, String international_phone_number ){
        this.name = name;
        this.formatted_address = formatted_address;
        this.formatted_phone_number = formatted_phone_number;
        this.international_phone_number = international_phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
    }
}
