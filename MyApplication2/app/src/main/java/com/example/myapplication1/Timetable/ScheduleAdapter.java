package com.example.myapplication1.Timetable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.SetViewHolder> {

    Context context;
    Activity activity;
    List<TimetableModel> courseList;
    List<TimetableModel> newList;

    public ScheduleAdapter(Activity activity, List<TimetableModel> courseList) {
        this.activity = activity;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public SetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_schedule_item, parent, false);
        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.SetViewHolder holder, int position) {
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

    public static class SetViewHolder extends RecyclerView.ViewHolder {

        LetterImageView img_icon;
        TextView tv_coursename,tv_startTime,tv_endTime,tv_roomLocation;

        public SetViewHolder(View view) {
            super(view);
            img_icon = view.findViewById(R.id.img_icon);
            tv_coursename = view.findViewById(R.id.tv_coursename);
            tv_startTime = view.findViewById(R.id.tv_startTime);
            tv_endTime = view.findViewById(R.id.tv_endTime);
            tv_roomLocation = view.findViewById(R.id.tv_location);
        }
    }
}