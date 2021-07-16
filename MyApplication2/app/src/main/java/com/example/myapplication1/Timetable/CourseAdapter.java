package com.example.myapplication1.Timetable;

import android.app.Activity;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    List<TimetableModel> courseList;

    public CourseAdapter( Activity activity, List<TimetableModel> courseList) {
        this.activity = activity;
        this.courseList = courseList;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {
        holder.course_name.setText(courseList.get(position).getCourseName());
        holder.course_code.setText(courseList.get(position).getCourseCode());
        holder.daysSel.setText(courseList.get(position).getDays());
        holder.start_time.setText(courseList.get(position).getStartTime());
        holder.end_time.setText(courseList.get(position).getEndTime());
        holder.professor.setText(courseList.get(position).getProfessor());
        holder.location.setText(courseList.get(position).getRoomLocation());/*
        holder.description.setText(courseList.get(position).getDescription());*/
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "working lols", Toast.LENGTH_SHORT).show();
/*                Intent intent = new Intent(context, UpdateCourseActivity.class);
                activity.startActivity(intent);
                intent.putExtra("", String.valueOf(courseList.get(position).getCourseName()));
                intent.putExtra("", String.valueOf(courseList.get(position).getCourseCode()));
                intent.putExtra("", String.valueOf(courseList.get(position).getStartTime()));
                intent.putExtra("mon", courseList.get(position).getMon());
                intent.putExtra("tues", courseList.get(position).getTues());
                intent.putExtra("wed", courseList.get(position).getWed());
                intent.putExtra("thurs", courseList.get(position).getThurs());
                intent.putExtra("fri", courseList.get(position).getFri());
                intent.putExtra("sat", courseList.get(position).getSat());
                intent.putExtra("sun", courseList.get(position).getSun());
                intent.putExtra("", String.valueOf(courseList.get(position).getEndTime()));
                intent.putExtra("", String.valueOf(courseList.get(position).getProfessor()));
                intent.putExtra("", String.valueOf(courseList.get(position).getRoomLocation()));
                intent.putExtra("", String.valueOf(courseList.get(position).getDescription()));
                activity.startActivityForResult(intent, 1);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_name, course_code, start_time, end_time, professor, location, description, daysSel;
        RelativeLayout mainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.tv_coursename2);
            course_code = itemView.findViewById(R.id.tv_coursecode2);
            start_time = itemView.findViewById(R.id.tv_starttime2);
            end_time = itemView.findViewById(R.id.tv_endtime2);
            professor = itemView.findViewById(R.id.tv_professor2);
            location = itemView.findViewById(R.id.tv_location2);
            daysSel = itemView.findViewById(R.id.days2);/*
            description = itemView.findViewById(R.id.input_description);*/
            mainlayout = itemView.findViewById(R.id.courselistmainlayout);
        }
    }
}
