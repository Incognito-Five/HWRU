package com.example.myapplication1.Timetable;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication1.R;

import java.util.ArrayList;
import java.util.List;


public class D1_Monday extends Fragment {

    private TimetableDBHelper dbHelper;
    private List<TimetableModel> models;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d1__monday, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.list_d1);
        models = new ArrayList<>();
        dbHelper = new TimetableDBHelper(getActivity());

        fetchAllCourseFromDataBase();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ScheduleAdapter adapter = new ScheduleAdapter(getActivity(), models);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void fetchAllCourseFromDataBase() {
        Cursor cursor = dbHelper.getMonday();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No Schedule set on Monday", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                models.add(new TimetableModel(cursor.getString(0), cursor.getString(3), cursor.getString(4), cursor.getString(6)));
            }
        }
    }
}