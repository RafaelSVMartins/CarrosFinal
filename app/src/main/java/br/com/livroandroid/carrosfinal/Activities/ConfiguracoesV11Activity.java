package br.com.livroandroid.carrosfinal.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;

import br.com.livroandroid.carrosfinal.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ConfiguracoesV11Activity extends android.app.Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new PrefsFragment());
    }
    public static class PrefsFragment extends android.preference.PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
