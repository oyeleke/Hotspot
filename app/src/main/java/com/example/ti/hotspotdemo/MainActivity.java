package com.example.ti.hotspotdemo;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    String Places;
    TextView txtOutputLat, txtOutputLon,show;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    String lat, lon;
    double longitude,latitude;
    EditText read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);
        // read = (EditText)findViewById(R.id.Edit_query);
        //txtOutputLat = (TextView)findViewById(R.id.text);
        //txtOutputLon =(TextView)findViewById(R.id.text02);
        //  show =(TextView)findViewById(R.id.text03);


        buildGoogleApiClient();
    }


    @Override
    public void onConnected(Bundle bundle) {


        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100); // Update location every second

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            //  lat = String.valueOf(latitude);
            //  lon = String.valueOf(longitude);
            //  updateui();

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        try{
        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();}
        catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, getString(R.string.Network) , Toast.LENGTH_SHORT).show();
        }
        //lat = String.valueOf(latitude);
        //lon = String.valueOf(longitude);
        //updateui();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        buildGoogleApiClient();
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }
    void updateui(){
        txtOutputLon.setText(lon);
        txtOutputLat.setText(lat);
        show.setText(Places);
    }


    public void passOnValues(){
        //EditText read = (EditText)view.findViewById(R.id.Edit_query);

        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("Places", Places);
        startActivity(intent);

    }
    public void onBankClick(View view){
        Places = "bank";
        passOnValues();
    }
    public void onBusClick(View view){
        Places = "bus_station";
        passOnValues();
    }
    public void onMoreClick(View view){

        Intent intent = new Intent(MainActivity.this, Main22Activity.class);
        intent.putExtra("latitude",latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);

    }

    public void onClickNews(View view){
        Intent intent = new Intent(MainActivity.this,Main5Activity.class);
        startActivity(intent);
    }

}

