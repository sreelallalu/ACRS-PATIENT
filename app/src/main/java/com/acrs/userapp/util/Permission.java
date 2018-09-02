package com.acrs.userapp.util;

import android.Manifest;

public interface Permission {
    String[] camera=new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.LOCATION_HARDWARE,
    };
 String[] location=new String[]{

         Manifest.permission.ACCESS_COARSE_LOCATION,
         Manifest.permission.ACCESS_FINE_LOCATION,
    };

}
