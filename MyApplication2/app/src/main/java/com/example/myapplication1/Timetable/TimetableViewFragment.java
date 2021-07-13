package com.example.myapplication1.Timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication1.R;
import com.google.android.material.tabs.TabLayout;

public class TimetableViewFragment extends Fragment {

    TabLayout tab_week;
    ViewPager2 vp;
    FragmentAdapter2 adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable_view, container, false);

        tab_week = view.findViewById(R.id.tab_week);
        vp = view.findViewById(R.id.vp);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        adapter2 = new FragmentAdapter2(fm,getLifecycle());
        vp.setAdapter(adapter2);

        tab_week.addTab(tab_week.newTab().setText("Monday"));
        tab_week.addTab(tab_week.newTab().setText("Tuesday"));
        tab_week.addTab(tab_week.newTab().setText("Wednesday"));
        tab_week.addTab(tab_week.newTab().setText("Thursday"));
        tab_week.addTab(tab_week.newTab().setText("Friday"));
        tab_week.addTab(tab_week.newTab().setText("Saturday"));
        tab_week.addTab(tab_week.newTab().setText("Sunday"));

        tab_week.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}