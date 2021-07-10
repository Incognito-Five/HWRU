package com.example.myapplication1.days;

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
import com.example.myapplication1.Timetable.ScheduleAdapter;
import com.example.myapplication1.Timetable.TimetableDBHelper;
import com.example.myapplication1.Timetable.TimetableModel;

import java.util.ArrayList;
import java.util.List;

public class D5_Friday extends Fragment {

    private TimetableDBHelper dbHelper;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private Cursor cursor;
    private List<TimetableModel> models;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d5__friday, container, false);

        recyclerView = view.findViewById(R.id.list_d5);

        models = new ArrayList<>();
        dbHelper = new TimetableDBHelper(getActivity());
        fetchAllCourseFromDataBase();

        return view;
    }

    private void fetchAllCourseFromDataBase() {
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No Schedule set on Friday", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                models.add(new TimetableModel(cursor.getString(0), cursor.getString(3), cursor.getString(4), cursor.getString(6)));
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ScheduleAdapter(getActivity(), models);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}