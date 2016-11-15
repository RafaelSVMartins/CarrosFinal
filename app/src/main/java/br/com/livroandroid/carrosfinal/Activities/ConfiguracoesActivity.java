package br.com.livroandroid.carrosfinal.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import br.com.livroandroid.carrosfinal.R;
import br.com.livroandroid.carrosfinal.Utils.PrefsUtils;

@SuppressWarnings("deprecation")
public class ConfiguracoesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String key="PREF_CHECK_PUSH";
    private static boolean b=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("PREF_CHECK_PUSH")) {
                Preference connectionPref = findPreference(key);
                b = PrefsUtils.isCheckPushOn(getActivity());
                connectionPref.setSelectable(b);
            }
    }

    private Context getActivity() {
        return this;
    }
}
