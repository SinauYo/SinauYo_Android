package com.example.android.sinauYo;

import android.app.Fragment;
import android.app.FragmentManager;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        NavigationView nav=(NavigationView)findViewById(R.id.navigation_view);
        nav.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //actionBar = getSupportActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#32be98")));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.navigation,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        //Mendapatkan id dari item yang
//        int id = item.getItemId();
//        //Apabila item yang dipilih adalah setting
//        if (id==R.id.navigation_logout){
//            //Membuat intent baru dari list to do ke pengaturan
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            //Memulai intent
//            startActivity(intent);
//            //Menutup activity setelah berhasil dilakukan
//            finish();
//        }
//        return true;
//    }

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id =item.getItemId();
//        if (id==R.id.navigation_logout){
//            onLogout();
//        }
////        if(mToggle.onOptionsItemSelected(item)){
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

//    public void onLogout() {
//            Toast.makeText(this, "See you later!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//    }

//    public void selectItem(MenuItem item){
//        Fragment myfragment =null;
//        Class fragmentClass;
//        switch (item.getItemId()){
//            case R.id.navigation_logout:
//            fragmentClass=LoginActivity.class;
//            break;
//
//            default:
//                fragmentClass=MainActivity.class;
//        }
//        try {
//            myfragment=(Fragment)fragmentClass.newInstance();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        FragmentManager manager= getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.item_view,myfragment).commit();
//        item.setChecked(true);
//        setTitle(item.getTitle());
//        mDrawerLayout.closeDrawers();
//    }



}
