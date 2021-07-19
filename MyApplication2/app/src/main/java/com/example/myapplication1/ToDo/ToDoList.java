package com.example.myapplication1.ToDo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication1.AccountFragment;
import com.example.myapplication1.BackUpFragment;
import com.example.myapplication1.Help.HelpFragment;
import com.example.myapplication1.Homepage;
import com.example.myapplication1.R;
import com.example.myapplication1.SoundsFragment;
import com.example.myapplication1.ThemesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoList extends AppCompatActivity implements OnDialogCloseListener, NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView toDoListRecyclerview;
    private FloatingActionButton toDoListFab;
    private ToDoDBHelper toDoDB;
    private List<ToDoModel> toDoList;
    private ToDoAdapter adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        toDoListRecyclerview = findViewById(R.id.toDoListRecyclerview);
        toDoListFab = findViewById(R.id.toDoListFab);
        toDoDB = new ToDoDBHelper(ToDoList.this);
        toDoList = new ArrayList<>();
        adapter = new ToDoAdapter(toDoDB, ToDoList.this);

        toDoList = toDoDB.getAllTasks();
        Collections.reverse(toDoList);
        adapter.setTasks(toDoList);

        toolbar = findViewById(R.id.tb_toDoList);
        setSupportActionBar(toolbar);

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toDoListRecyclerview.setHasFixedSize(true);
        toDoListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        toDoListRecyclerview.setAdapter(adapter);



        //will go to new activity when the fab is clicked
        toDoListFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoAddTask.newInstance().show(getSupportFragmentManager(),ToDoAddTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewHelper(adapter));
        itemTouchHelper.attachToRecyclerView(toDoListRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        toDoList = toDoDB.getAllTasks();
        Collections.reverse(toDoList);
        adapter.setTasks(toDoList);
        adapter.notifyDataSetChanged();
    }
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