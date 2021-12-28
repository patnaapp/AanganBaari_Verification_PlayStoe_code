package bih.in.aanganwadiBenVerification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.ContextCompat;

import bih.in.aanganwadiBenVerification.R;

public class FakeLocationDetecter {

    FakeLocatationDetectorListener listener;
    static Location LastLocation = null;
    LocationManager mlocManager = null;
    Context context;

    FakeLocationDetecter(Location location, Context context){

        this.context = context;

//        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, (float) 0.01, mlistener);
//        mlocManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER, 5000, 10, mlistener);
//        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, (float) 0.01, mlistener);
//        //  mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, (float) 0.01, mlistener);
//
//        mlocManager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER, 5000, 10, mlistener);

        if(isMockLocationOn(location, context)){
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.logo1)
                    .setTitle("चेतावनी!")
                    .setMessage("इस एप्लिकेशन को जारी रखने के लिए, कृपया Mock Location को developer option से disable करें और Fake Location App को बंद करें")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            listener.onExit();
                        }
                    })
                    .show();
        }else{
            listener.onContinue();
        }
    }

    public boolean isMockLocationOn(Location location, Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return location.isFromMockProvider();
        } else {
            String mockLocation = "0";
            try {
                mockLocation = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return !mockLocation.equals("0");
        }
    }

    private boolean checkLocationPermission() {
        if (!hasLocationPermission()) {
            Log.e("Tuts+", "Does not have location permission granted");
            //requestLocationPermission();
            return false;
        }

        return true;
    }

    private boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private final static int REQUEST_PERMISSION_RESULT_CODE = 42;



    private final LocationListener mlistener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            isMockLocationOn(location,context);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    };
}

