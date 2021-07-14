package com.example.myapplication1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class BMI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;//inclu
    FloatingActionButton floatingActionButton;
    //ImageButton imageButton;//inclu

    BMIDatabase bmidb;
    ArrayList<String> bmiid,bmiresult,bmicategory;
    BMICustomAdapter customAdapter;
    CoordinatorLayout coordinatorLayout;
    DrawerLayout drawerLayout;

    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        recyclerView=findViewById(R.id.BMIRecyclerView);
        floatingActionButton=findViewById(R.id.BMICalcuBttn);
        coordinatorLayout = findViewById(R.id.bmi_layout);

        //will go to new activity when the fab is clicked
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BMI.this,BMICalculator.class);
                startActivity(intent);
            }
        });

        bmidb= new BMIDatabase(BMI.this);
        bmiid = new ArrayList<>();
        bmiresult = new ArrayList<>();
        bmicategory = new ArrayList<>();

        storeDatainArrays();

        customAdapter = new BMICustomAdapter(BMI.this, bmiid, bmiresult, bmicategory);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BMI.this));

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDatainArrays() {
        Cursor cursor = bmidb.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                bmiid.add(cursor.getString(0));
                bmiresult.add(cursor.getString(1));
                bmicategory.add(cursor.getString(2));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.bmioptionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //delete all notes when clicked
        if (item.getItemId()==R.id.delete)
        {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BMIDatabase db=new BMIDatabase(BMI.this);
                Toast.makeText(BMI.this, "Deleted All", Toast.LENGTH_SHORT).show();
                db.deleteAll();
                //refresh activity
                Intent intent = new Intent(BMI.this,BMI.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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