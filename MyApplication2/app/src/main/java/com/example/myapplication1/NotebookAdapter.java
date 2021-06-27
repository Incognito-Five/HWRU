package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.MyViewHolder> {
    //a class that will show contents per card on the main notebook activity
    Context context;
    Activity activity;
    List<NotebookModel> noteslist;

    public NotebookAdapter(Context context, Activity activity, List<NotebookModel> noteslist) {
        this.context = context;
        this.activity = activity;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notebook_recyclerview_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(noteslist.get(position).getTitle());
        holder.content.setText(noteslist.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
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
