package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Notebook extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    NotebookAdapter adapter;
    List<NotebookModel> notebookModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        recyclerView=findViewById(R.id.notebookrecyclerview);
        fab=findViewById(R.id.notebookfab);

        //will go to new activity when the fab is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Notebook.this,Notebook_AddNotesActivity.class);
                startActivity(intent);
            }
        });

        notebookModelList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter instance
        adapter= new NotebookAdapter(this, Notebook.this,notebookModelList);
        //set adapter to recycler view
        recyclerView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notebook_options_menu,menu);
        //search bar
        MenuItem searchItem=menu.findItem(R.id.search_notebook);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Note Here");

        //shows texts in the search bar
        SearchView.OnQueryTextListener listener=new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(listener);

        return super.onCreateOptionsMenu(menu);
    }
}