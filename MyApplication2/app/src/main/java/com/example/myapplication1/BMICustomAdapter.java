package com.example.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class BMICustomAdapter extends RecyclerView.Adapter<BMICustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList bmiid,bmiresult,bmicategory;

    Animation bmi_translate_anim;


    BMICustomAdapter(Context context,
                     ArrayList bmiid,
                     ArrayList bmiresult,
                     ArrayList bmicategory){

        this.context = context;
        this.bmiid = bmiid;
        this.bmiresult = bmiresult;
        this.bmicategory = bmicategory;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bmi_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BMICustomAdapter.MyViewHolder holder, int position) {
        //get string from array
        holder.bmiid.setText(String.valueOf(bmiid.get(position)));
        holder.bmiresult.setText(String.valueOf(bmiresult.get(position)));
        holder.bmicategory.setText(String.valueOf(bmicategory.get(position)));
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return bmiid.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bmiid, bmiresult, bmicategory;
        LinearLayout bmimainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bmiid = itemView.findViewById(R.id.bmiid);
            bmiresult = itemView.findViewById(R.id.bmiresult);
            bmicategory = itemView.findViewById(R.id.bmicategory);
            bmimainlayout = itemView.findViewById(R.id.bmimainlayout);
            //animate recyclerview
            bmi_translate_anim = AnimationUtils.loadAnimation(context, R.anim.bmi_translate_anim);
            bmimainlayout.setAnimation(bmi_translate_anim);


        }
    }

}
