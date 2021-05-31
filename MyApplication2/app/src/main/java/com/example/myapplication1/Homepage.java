package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView card_timetable, card_calendar, card_notebook, card_bmi, card_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);
        card_timetable = (CardView) findViewById(R.id.card_timetable);
        card_calendar = (CardView) findViewById(R.id.card_calendar);
        card_notebook = (CardView) findViewById(R.id.card_notebook);
        card_bmi = (CardView) findViewById(R.id.card_bmi);
        card_water = (CardView) findViewById(R.id.card_water);

        //Card View
        card_timetable.setOnClickListener(this);
        card_calendar.setOnClickListener(this);
        card_notebook.setOnClickListener(this);
        card_bmi.setOnClickListener(this);
        card_water.setOnClickListener(this);

        //Tool Bar
        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.card_timetable: openIntent(Timetable.class); break;
            case R.id.card_calendar: openIntent(Calendar.class); break;
            case R.id.card_notebook: openIntent(Notebook.class); break;
            case R.id.card_bmi: openIntent(BMI.class); break;
            case R.id.card_water: openIntent(WaterTracker.class); break;
        }

    }

    public void openIntent(Class classname){
        Intent intent = new Intent(this, classname);
        startActivity(intent);
    }
}