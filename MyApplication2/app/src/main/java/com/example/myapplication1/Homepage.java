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

public class Homepage extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView card_timetable, card_calendar, card_notebook, card_bmi, card_water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        Intent intent = new Intent(getApplicationContext(), HeaderActivity.class);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Hooks

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
            case R.id.back_up_storage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BackUpFragment()).commit();
                break;
            case R.id.themes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThemesFragment()).commit();
                break;
            case R.id.help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                break;
            case R.id.sounds:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SoundsFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}