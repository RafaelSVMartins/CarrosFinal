package br.com.livroandroid.carrosfinal.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.livroandroid.carrosfinal.Fragments.AboutDialogFragment;
import br.com.livroandroid.carrosfinal.Fragments.CarrosTabFragment;
import br.com.livroandroid.carrosfinal.R;

public class MainActivity extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setuptoolbar();
        setUpDrawer();
        replaceFragment(new CarrosTabFragment());
        //nreplacefragment(new CarrosTabFragment());
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void nreplacefragment(Fragment fr) {
        getSupportFragmentManager().beginTransaction().add(R.id.container4,fr).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(this,"Clicou no sobre",Toast.LENGTH_SHORT).show();
            AboutDialogFragment.showAbout(getSupportFragmentManager());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
