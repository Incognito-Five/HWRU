package com.example.myapplication1.Timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication1.AccountFragment;
import com.example.myapplication1.BackUpFragment;
import com.example.myapplication1.HelpFragment;
import com.example.myapplication1.Homepage;
import com.example.myapplication1.Notebook;
import com.example.myapplication1.Notebook_AddNotesActivity;
import com.example.myapplication1.R;
import com.example.myapplication1.SoundsFragment;
import com.example.myapplication1.ThemesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class Timetable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        fab = findViewById(R.id.fab_course);
        toolbar = findViewById(R.id.tb_ttable);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new ScheduleFragment()).commit();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Timetable.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.sched:
                            fragment = new ScheduleFragment();
                            break;
                        case R.id.course:
                            fragment = new CoursesFragment();
                            break;
                        case R.id.timetable_setting:
                            fragment = new SettingsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, fragment).commit();
                    return true;
                }
            };
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, Homepage.class);
                startActivity(intent);
                break;
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