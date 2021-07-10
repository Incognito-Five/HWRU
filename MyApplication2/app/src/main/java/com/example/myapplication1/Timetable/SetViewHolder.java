package com.example.myapplication1.Timetable;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

public class SetViewHolder extends RecyclerView.ViewHolder{

    public LetterImageView img_icon;
    public TextView tv_coursename;
    public TextView tv_startTime;
    public TextView tv_endTime;
    public TextView tv_roomLocation;

    public SetViewHolder(View view){
        super(view);
        img_icon = view.findViewById(R.id.img_icon);
        tv_coursename = view.findViewById(R.id.tv_coursename);
        tv_startTime = view.findViewById(R.id.tv_startTime);
        tv_endTime =view.findViewById(R.id.tv_endTime);
        tv_roomLocation = view.findViewById(R.id.tv_location);
    }
}
