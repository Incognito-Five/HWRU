package com.example.myapplication1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

public class TimetableViewFragment extends Fragment {


    private static final int TABLE_COL = 9;
    private static final int TABLE_ROW = 14;

    TimetableDBHelper DB;
    TableLayout tableLayout;

    public TimetableViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable_view, container, false);

        tableLayout = view.findViewById(R.id.table);

        return view;
    }
}