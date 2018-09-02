package com.acrs.userapp.ui.emergency.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by sreelal on 24/8/17.
 */

public class LocationHelper {

    public static final int LOCATION_PERMISION = 555;




    public static boolean locationON(Context context)throws Exception
    {  boolean check=false;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isGPSEnabled == false && isNetworkProviderEnabled == false) {
            check=false;

            Toast.makeText(context, "Turn On Your Location", Toast.LENGTH_SHORT).show();

        } else{
            check=true;
        }
     return check;
    }



    public static Location getLocation(Context context)  {
        try {




            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isGPSEnabled == false && isNetworkProviderEnabled == false) {


                    Toast.makeText(context, "Turn On Your GPS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent);

            }

            {
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


                        Toast.makeText(context, "Please Enable App Permissions", Toast.LENGTH_SHORT).show();


                        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISION);
                        } else {
                            //  Toast.makeText(getApplicationContext(), "Turn On Your GPS", Toast.LENGTH_LONG).show();
                            Toast.makeText(context, "Turn On Your GPS", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        //  Toast.makeText(getApplicationContext(),"Turn On Your GPS",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }

                    //  return null;
                }


                Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                long GPSLocationTime = 0;
                if (null != locationGPS) {
                    GPSLocationTime = locationGPS.getTime();
                }

                long NetLocationTime = 0;

                if (null != locationNet) {
                    NetLocationTime = locationNet.getTime();
                }

                if (0 < GPSLocationTime - NetLocationTime) {

                    return locationGPS;
                } else {
                    return locationNet;
                }


            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
            }

    }


}