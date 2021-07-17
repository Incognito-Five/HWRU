package com.example.myapplication1.Timetable;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    Activity activity;
    List<TimetableModel> courseList;
    List<TimetableModel> newList;

    public CourseAdapter(Activity activity, List<TimetableModel> courseList) {
        this.activity = activity;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_course_item, parent, false);
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
        holder.location.setText(courseList.get(position).getRoomLocation());
        holder.description.setText(courseList.get(position).getDescription());
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), UpdateCourseActivity.class);
                intent.putExtra("course_name", courseList.get(position).getCourseName());
                intent.putExtra("course_code", courseList.get(position).getCourseCode());
                intent.putExtra("start_time", courseList.get(position).getStartTime());
                intent.putExtra("end_time", courseList.get(position).getEndTime());
                intent.putExtra("mon", courseList.get(position).getMon());
                intent.putExtra("tues", courseList.get(position).getTues());
                intent.putExtra("wed", courseList.get(position).getWed());
                intent.putExtra("thurs", courseList.get(position).getThurs());
                intent.putExtra("fri", courseList.get(position).getFri());
                intent.putExtra("sat", courseList.get(position).getSat());
                intent.putExtra("sun", courseList.get(position).getSun());
                intent.putExtra("prof", courseList.get(position).getProfessor());
                intent.putExtra("loc", courseList.get(position).getRoomLocation());
                intent.putExtra("desc", courseList.get(position).getDescription());
                activity.startActivity(intent);
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
            daysSel = itemView.findViewById(R.id.days2);
            description = itemView.findViewById(R.id.tv_desc2);
            mainlayout = itemView.findViewById(R.id.courselistmainlayout);
        }
    }
}
