package br.com.livroandroid.carrosfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.livroandroid.carrosfinal.Fragments.CarrosFragment;
import br.com.livroandroid.carrosfinal.Fragments.CarrosTabFragment;
import br.com.livroandroid.carrosfinal.Fragments.SiteLivroFragment;
import br.com.livroandroid.carrosfinal.R;
import livroandroid.lib.utils.AndroidUtils;


public class Base_Activity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected FloatingActionButton fab;
    protected ActionBarDrawerToggle toggle;

    protected void setuptoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    protected void setUpDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                    if (id == R.id.frag1) {
                        Intent intent = new Intent(getBaseContext(),CarrosActivity.class);
                        intent.putExtra("tipo",R.string.classicos);
                        startActivity(intent);
                    } else if (id == R.id.frag2) {
                        Intent intent = new Intent(getBaseContext(),CarrosActivity.class);
                        intent.putExtra("tipo",R.string.esportivos);
                        startActivity(intent);
                    } else if (id == R.id.frag3) {
                        Intent intent = new Intent(getBaseContext(),CarrosActivity.class);
                        intent.putExtra("tipo",R.string.luxo);
                        startActivity(intent);
                    } else if (id == R.id.frag4) {
                        replaceFragment(new SiteLivroFragment());
                    } else if (id == R.id.nav_item_settings) {
                        /*if (AndroidUtils.isAndroid3Honeycomb()) {
                            startActivity(new Intent(getBaseContext(),ConfiguracoesV11Activity.class));
                        } else {
                            startActivity(new Intent(getBaseContext(),ConfiguracoesActivity.class));
                        }*/
                        startActivity(new Intent(getBaseContext(),ConfiguracoesActivity.class));
                    }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void replaceFragment(Fragment frag) {

        this.getSupportFragmentManager().beginTransaction().add(R.id.content_base_,frag).commit();
    }
}
