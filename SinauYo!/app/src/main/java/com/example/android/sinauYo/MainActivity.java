package com.example.android.sinauYo;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {    //

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nav_open, R.string.nav_close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView nav=(NavigationView)findViewById(R.id.navigation_view);  //
        nav.setNavigationItemSelectedListener(this);    //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //


        //actionBar = getSupportActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#32be98")));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int index =item.getItemId();
        if (index==R.id.navigation_logout){
            //Membuat intent baru dari list to do ke pengaturan
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //Memulai intent
            startActivity(intent);
            //Menutup activity setelah berhasil dilakukan
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }




}
