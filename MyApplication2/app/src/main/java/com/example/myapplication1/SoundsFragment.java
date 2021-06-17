package com.example.myapplication1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SoundsFragment extends Fragment {

    ImageView sounds;
    MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sounds, container, false);

        sounds = (ImageView) rootView.findViewById(R.id.sound_icon);
        mp = MediaPlayer.create(SoundsFragment.this.getActivity(), R.raw.alarm);
        mp.start();
        return rootView;
    }
}