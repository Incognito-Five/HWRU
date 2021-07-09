package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    Activity activity;
    List<Model> resultslist;

    public Adapter(Context context, Activity activity, List<Model> resultslist) {
        this.context = context;
        this.activity = activity;
        this.resultslist = resultslist;
    }

    //recyclerview
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bmiresultitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.bmiresult.setInputType((int) resultslist.get(position).getBmiresult());
        holder.bmicategory.setText(resultslist.get(position).getBmicategory());

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return resultslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bmiresult,bmicategory;
        RelativeLayout bmiresultlayout;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            bmiresult=itemView.findViewById(R.id.bmiresult);
            bmicategory=itemView.findViewById(R.id.bmicategory);
            bmiresultlayout=itemView.findViewById(R.id.bmiresultlayout);
        }
    }
    public List<Model> getList(){
        return resultslist;
    }

    //method for removing a note
    public void removeItem(int position){
        resultslist.remove(position);
        notifyItemRemoved(position);
    }

    //method for restoring a note
    public void restoreItem(Model item, int position){
        resultslist.add(position,item);
        notifyItemInserted(position);
    }
}
