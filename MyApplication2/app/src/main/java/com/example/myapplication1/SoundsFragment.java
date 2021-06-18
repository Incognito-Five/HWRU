package com.example.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SoundsFragment extends Fragment {

    SeekBar notif_bar, alarm_bar;
    Button play, pause;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sounds, container, false);

        mediaPlayer = MediaPlayer.create(SoundsFragment.this.getActivity(), R.raw.alarm);
        audioManager = (AudioManager) SoundsFragment.this.getActivity().getSystemService(Context.AUDIO_SERVICE);

        //volumes
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //seekbar
        notif_bar = (SeekBar) rootView.findViewById(R.id.notif_volume);

        //alarm seekbar
        alarm_bar = (SeekBar) rootView.findViewById(R.id.alarm_volume);
        alarm_bar.setMax(maxVolume);
        alarm_bar.setProgress(currVolume);

        alarm_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //buttons
        play = (Button) rootView.findViewById(R.id.play_btn);
        pause = (Button) rootView.findViewById(R.id.pause_btn);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        return rootView;
    }
}