package com.example.myapplication1.Timetable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication1.days.D1_Monday;
import com.example.myapplication1.days.D2_Tuesday;
import com.example.myapplication1.days.D3_Wednesday;
import com.example.myapplication1.days.D4_Thursday;
import com.example.myapplication1.days.D5_Friday;
import com.example.myapplication1.days.D6_Saturday;
import com.example.myapplication1.days.D7_Sunday;

public class FragmentAdapter2 extends FragmentStateAdapter {

    public FragmentAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new D2_Tuesday();
            case 2:
                return new D3_Wednesday();
            case 3:
                return new D4_Thursday();
            case 4:
                return new D5_Friday();
            case 5:
                return new D6_Saturday();
            case 6:
                return new D7_Sunday();
        }
        return new D1_Monday();
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}