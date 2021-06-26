package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.MyViewHolder> {

    Context context;
    Activity activity;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //details that can be seen on the recyclerview
        TextView title,content;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.notebooktitle);
            content=itemView.findViewById(R.id.notebookcontent);
            layout=itemView.findViewById(R.id.notebooklayout);
        }
    }
}
