package com.example.myapplication1.Timetable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<SetViewHolder> {

    Context context;
    Activity activity;
    List<TimetableModel> courseList;
    List<TimetableModel> newList;

    public ScheduleAdapter(Activity activity, List<TimetableModel> courseList){
        this.activity = activity;
        this.courseList = courseList;
    }

    public @NotNull SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card, parent, false);
        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {
        holder.tv_coursename.setText(courseList.get(position).getCourseName());
        holder.tv_startTime.setText(courseList.get(position).getStartTime());
        holder.tv_endTime.setText(courseList.get(position).getEndTime());
        holder.tv_roomLocation.setText(courseList.get(position).getRoomLocation());
        holder.img_icon.setmLetter(courseList.get(position).getCourseName().charAt(0));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

}