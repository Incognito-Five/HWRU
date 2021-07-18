package com.example.myapplication1.ToDo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> toDoList;
    private ToDoList activity;
    private ToDoDBHelper toDoDB;

    public ToDoAdapter(ToDoDBHelper toDoDB, ToDoList activity){
        this.activity = activity;
        this.toDoDB = toDoDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = toDoList.get(position);
        holder.mcheckbox.setText(item.getTask());
        holder.mcheckbox.setChecked(toBoolean(item.getStatus()));
        holder.mcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    toDoDB.updateStatus(item.getId(), 1);
                }
                else{
                    toDoDB.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public boolean toBoolean(int num){
        return num!= 0;
    }

    public Context getContext(){
        return activity;
    }

    public void setTasks(List<ToDoModel> toDoList){
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    public void  deleteTask(int position){
        ToDoModel item = toDoList.get(position);
        toDoDB.deleteTask(item.getId());
        toDoList.remove(position);
        notifyItemRemoved(position);
    }

    public  void updateTask(int position){
        ToDoModel item = toDoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        ToDoAddTask task = new ToDoAddTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }
    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox mcheckbox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mcheckbox = itemView.findViewById(R.id.mcheckbox);
        }
    }
}
