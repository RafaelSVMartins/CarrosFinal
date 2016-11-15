package br.com.livroandroid.carrosfinal;

import android.app.Application;
import android.util.Log;

import com.squareup.otto.Bus;

/**
 * Created by Rrafael on 14/11/2016.
 */

public class CarrosApplication extends Application {
    private static final String TAG ="CarrosApplication";
    private static CarrosApplication instance = null;
    private Bus bus = new Bus();

    public Bus getBus() {
        return bus;
    }

    public static CarrosApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "CarrosApplication onCreate()");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "CarrosApplication onTerminate()");

    }
}
