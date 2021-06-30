package com.example.myapplication1;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class Permissions {

    public static final int REQUEST_CODE_PERMISSIONS = 2;

    // Storage Permissions variables
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //check permissions.
    public static void verifyStoragePermissions(BackUpFragment backUpFragment) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(backUpFragment.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(backUpFragment.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    backUpFragment.getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_CODE_PERMISSIONS
            );
        }
    }
}
