package com.example.myapplication1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Help.HelpFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Notebook extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    NotebookAdapter adapter;
    List<NotebookModel> notebookModelList;
    NotebookDatabaseClass databaseClass;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        recyclerView=findViewById(R.id.notebookrecyclerview);
        fab=findViewById(R.id.notebookfab);
        coordinatorLayout = findViewById(R.id.notebook_layout);
        toolbar = findViewById(R.id.tb_nbook);
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

        //will go to new activity when the fab is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Notebook.this,Notebook_AddNotesActivity.class);
                startActivity(intent);
            }
        });


        notebookModelList=new ArrayList<>();
        //database instance class
        databaseClass=new NotebookDatabaseClass(this);
        //method to fetch all notes from database
        fetchAllNotesFromDataBase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter instance
        adapter= new NotebookAdapter(this, Notebook.this,notebookModelList);
        //set adapter to recycler view
        recyclerView.setAdapter(adapter);

        //itemtouchhelper instance class
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    void fetchAllNotesFromDataBase()
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
                notebookModelList.add(new NotebookModel(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }
        }
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
                adapter.getFilter().filter(newText);
                return true;
            }
        };

        searchView.setOnQueryTextListener(listener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //delete all notes when clicked
        if (item.getItemId()==R.id.delete_all_notebooks)
        {
            deleteAllNotebooks();
            
        }
        return super.onOptionsItemSelected(item);
    }

    //delete notes
    private void deleteAllNotebooks()
    {
        NotebookDatabaseClass db = new NotebookDatabaseClass(Notebook.this);
        db.deleteAllNotebooks();
        recreate();
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
            NotebookModel item = adapter.getList().get(position);

            adapter.removeItem(viewHolder.getAdapterPosition());

            //displays a text for "note deleted" and "undo"
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Note Deleted", Snackbar.LENGTH_LONG)
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
                                NotebookDatabaseClass db = new NotebookDatabaseClass(Notebook.this);
                                db.deleteSingleItem(item.getId());
                            }
                        }
                    });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
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