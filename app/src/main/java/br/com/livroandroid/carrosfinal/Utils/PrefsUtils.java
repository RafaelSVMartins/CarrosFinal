package br.com.livroandroid.carrosfinal.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Rrafael on 24/10/2016.
 */

public class PrefsUtils {
    public static boolean isCheckPushOn(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean("PREF_CHECK_PUSH",false);
    }
}
