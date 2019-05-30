package com.psi.gardenerasistance;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialize view
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.drawer_content, new HomeFragment())
                .commit();

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
        //getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        switch(id)
        {
            case R.id.nav_map:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new MapFragment())
                        .commit();
                break;
            case R.id.nav_sensors:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new SensorsFragment())
                        .commit();
                break;
            case R.id.nav_machinery:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new MachineryFragment())
                        .commit();
                break;
            case R.id.nav_workers:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new WorkersFragment())
                        .commit();
                break;
            case R.id.nav_messages:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new MessagesFragment())
                        .commit();
                break;
            case R.id.nav_permissions:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new PermissionsFragment())
                        .commit();
                break;
            case R.id.nav_settings:
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_content, new SettingsFragment())
                        .commit();
                break;
            case R.id.nav_change_position:
                Intent i = new Intent(NavigationDrawerActivity.this, HomeScreenActivity.class);
                startActivity(i);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
