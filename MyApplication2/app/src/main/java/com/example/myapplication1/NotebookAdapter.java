package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.MyViewHolder> implements Filterable {
    //a class that will show contents per card on the main notebook activity
    Context context;
    Activity activity;
    List<NotebookModel> noteslist;
    List<NotebookModel> newList;

    public NotebookAdapter(Context context, Activity activity, List<NotebookModel> noteslist) {
        this.context = context;
        this.activity = activity;
        this.noteslist = noteslist;
        newList= new ArrayList<>();
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

        //will move to the update notes screen
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context,Notebook_UpdateNotesActivity.class);

                intent.putExtra("title",noteslist.get(position).getTitle());
                intent.putExtra("content",noteslist.get(position).getContent());
                intent.putExtra("id",noteslist.get(position).getId());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    //method for filtering search lists
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<NotebookModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(newList);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (NotebookModel item:newList)
                {
                    if (item.getTitle().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        //for displaying the results
        protected void publishResults(CharSequence constraint, FilterResults results) {
        noteslist.clear();
        noteslist.addAll((List)results.values);
        notifyDataSetChanged();
        }
    };

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

    public List<NotebookModel> getList(){
        return noteslist;
    }

    //method for removing a note
    public void removeItem(int position){
        noteslist.remove(position);
        notifyItemRemoved(position);
    }

    //method for restoring a note
    public void restoreItem(NotebookModel item, int position){
        noteslist.add(position,item);
        notifyItemInserted(position);
    }
}
