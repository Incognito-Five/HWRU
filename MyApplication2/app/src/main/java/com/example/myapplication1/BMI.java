package com.example.myapplication1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class BMI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //calculate BMI
    android.widget.Button mcalculatebmi;

    //BMI Components
    TextView mcurrentheight;
    TextView mcurrentweight;
    SeekBar mheightseekbar;
    SeekBar mweightseekbar;

    //initialize BMI Component's Values
    int currentheightprogress;
    int currentweightprogress;
    String mintheightprogress="180";
    String mintweightprogress="40";

    //initialize all the views
    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> resultslist;
    CoordinatorLayout coordinatorLayout;
    BMIDatabaseClass databaseClass;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        recyclerView=findViewById(R.id.bmirecyclerview);
        coordinatorLayout = findViewById(R.id.bmicoordinatorlayout);

        //bmi results list
        resultslist=new ArrayList<>();
        //database instance class
        databaseClass=new BMIDatabaseClass(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter instance class
        adapter= new Adapter(this,BMI.this,resultslist);
        //set adapter to recycler view
        recyclerView.setAdapter(adapter);

        //itemtouchhelper instance class
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        getSupportActionBar().hide();
        //defining mcalculatebmi
        mcalculatebmi=findViewById(R.id.calculatebmi);
        //assign xml id to java
        mcurrentheight=findViewById(R.id.currentheight);
        mcurrentweight=findViewById(R.id.currentweight);
        //set id for seekbar
        mheightseekbar=findViewById(R.id.heightseekbar);
        mweightseekbar=findViewById(R.id.weightseekbar);

        mheightseekbar.setMax(400);
        mheightseekbar.setProgress(180);
        mheightseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentheightprogress=progress;
                mintheightprogress=String.valueOf(currentheightprogress);
                mcurrentheight.setText(mintheightprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mweightseekbar.setMax(650);
        mweightseekbar.setProgress(40);
        mweightseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentweightprogress=progress;
                mintweightprogress=String.valueOf(currentweightprogress);
                mcurrentweight.setText(mintweightprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*calculating BMI*/
                if(mintheightprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select A Value For Your Height First",Toast.LENGTH_SHORT).show();
                }
                else if(mintweightprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select A Value For Your Weight First",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    /*changing screen from main BMI screen to calculate BMI screen*/
                    Intent intent=new Intent(BMI.this,bmicalculation.class);
                    /*pass data to calculate BMI*/
                    intent.putExtra("height",mintheightprogress);
                    intent.putExtra("weight",mintweightprogress);


                    startActivity(intent);
                }

            }
        });


        toolbar = findViewById(R.id.tb_bmi);
        setSupportActionBar(toolbar);

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
    void fetchAllResultsFromDataBase()
    {
        Cursor cursor = databaseClass.readAllData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No Data to Show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                resultslist.add(new Model(cursor.getFloat(0),cursor.getString(1),cursor.getString(2)));
            }
        }
    }
    //for swiping note to the right
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override

        //method for swiping
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Model item = adapter.getList().get(position);

            adapter.removeItem(viewHolder.getAdapterPosition());

            //displays a text for "note deleted" and "undo"
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "BMI Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {

                        @Override
                        //restores the note when undo is clicked
                        public void onClick(View v) {
                            adapter.restoreItem(item,position);
                            recyclerView.scrollToPosition(position);
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);

                            if (!(event == DISMISS_EVENT_ACTION))
                            {
                                BMIDatabaseClass db = new BMIDatabaseClass(BMI.this);
                                db.deleteSingleItem(item.getId());
                            }
                        }
                    });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    };


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