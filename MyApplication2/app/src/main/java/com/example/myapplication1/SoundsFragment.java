package com.example.myapplication1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SoundsFragment extends Fragment {

    SeekBar volume_bar;
    Button alarm_sounds;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sounds, container, false);

        volume_bar = (SeekBar) rootView.findViewById(R.id.volume);
        alarm_sounds = (Button) rootView.findViewById(R.id.alarm_sounds);

        alarm_sounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoundsFragment.this.getActivity(), AlarmSoundsList.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}