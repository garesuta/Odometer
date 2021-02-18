package com.example.odometer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Binder;
import java.util.Random;
import android.location.LocationListener;
import android.location.Location;
import android.location.Criteria;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;

public class OdometerService extends Service {
    private final IBinder binder = new OdometerBinder();
    private final Random random = new Random();
    private LocationListener listener;
    private LocationManager locManager;
    public static final String PERMISSION_STRING = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    public void onCreate(){
        super.onCreate();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this,PERMISSION_STRING)== PackageManager.PERMISSION_GRANTED){
            String provider = locManager.getBestProvider(new Criteria(),true);
            if (provider != null){
                locManager.requestLocationUpdates(provider,1000,1,listener);
            }
        }
    }

    public class OdometerBinder extends Binder{
        OdometerService getOdometer(){
            return OdometerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double getDistance(){
        return random.nextDouble();
    }
}