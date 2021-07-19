package com.example.myapplication1.Help;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication1.AccountFragment;
import com.example.myapplication1.Homepage;
import com.example.myapplication1.R;


public class HelpFragment extends Fragment {

    Button btn_aboutUs, btn_aboutApp;
    ImageView home_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_help, container, false);
        btn_aboutUs = (Button) rootview.findViewById(R.id.btn_aboutUs);
        btn_aboutApp = (Button) rootview.findViewById(R.id.btn_aboutApp);
        home_icon = (ImageView) rootview.findViewById(R.id.home_icon);

        btn_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpFragment.this.getActivity(), AboutUs.class);
                startActivity(intent);
            }
        });

        btn_aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpFragment.this.getActivity(), AboutApp.class);
                startActivity(intent);
            }
        });
        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpFragment.this.getActivity(), Homepage.class);
                startActivity(intent);
            }
        });
        return rootview;

    }

}