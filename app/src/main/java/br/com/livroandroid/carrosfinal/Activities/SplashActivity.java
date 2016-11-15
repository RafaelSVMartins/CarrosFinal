package br.com.livroandroid.carrosfinal.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.carrosfinal.R;
import br.com.livroandroid.carrosfinal.Utils.PermissionUtils;
import livroandroid.lib.utils.AlertUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String permissions[] = new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        boolean ok = PermissionUtils.validated(this, 0, permissions);
        if (ok) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                AlertUtils.alert(getBaseContext(), R.string.app_name, R.string.msg_alerta_permisssao,
                        R.string.ok, new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
                return;
            }
        }
        startActivity(new Intent(this, MainActivity.class));
    }

}
