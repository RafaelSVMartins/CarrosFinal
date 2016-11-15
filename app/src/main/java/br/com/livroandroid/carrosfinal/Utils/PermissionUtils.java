package br.com.livroandroid.carrosfinal.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rrafael on 30/10/2016.
 */

public class PermissionUtils {

    public static boolean validated(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if(! ok) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            return true;
        }
        
        String[] newPremissions = new String[list.size()];
        list.toArray(newPremissions);
        ActivityCompat.requestPermissions(activity,newPremissions, 1);
        return false;
    }
}
