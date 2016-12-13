package com.example.ti.hotspotdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main22Activity extends AppCompatActivity {
    double lon;
    double lat;
    String Places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("latitude",0);
        lon = intent.getDoubleExtra("longitude",0);
    }
     //use of buttons to ensure google keywords re used yeah not all of them are connected to a view tho
    public void onClickLibary(View view){
        Places = "library";
        passOnValues();
    } public void onClickAtm(View view){
        Places = "atm";
        passOnValues();
    } public void onClickLodging(View view){
        Places = "lodging";
        passOnValues();
    } public void onClickPharmacy(View view){
        Places = "pharmacy";
        passOnValues();
    } public void onClickRestaurant(View view){
        Places = "restaurant";
        passOnValues();
    } public void onClickSchool(View view){
        Places = "school";
        passOnValues();
    }public void onClickGym(View view){
        Places = "gym";
        passOnValues();
    }public void onClickCityhall(View view){
        Places = "city_hall";
        passOnValues();
    }public void onClickMuseum(View view){
        Places = "museum";
        passOnValues();
    }public void onClickNightclub(View view){
        Places = "night_club";
        passOnValues();
    }public void onClickMosque(View view){
        Places = "mosque";
        passOnValues();
    }public void onClickChurch(View view){
        Places = "church";
        passOnValues();

    }public void onClickPolice(View view){
        Places = "police";
        passOnValues();
    }
    public void onBusClick(View view){
        Places = "bus_station";
        passOnValues();
    }


    public void passOnValues(){
        Intent intent = new Intent(Main22Activity.this, Main2Activity.class);
        intent.putExtra("latitude",lat);
        intent.putExtra("longitude", lon);
        intent.putExtra("Places", Places);
        startActivity(intent);

    }
}