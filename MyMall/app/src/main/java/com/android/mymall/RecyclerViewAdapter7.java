package com.android.mymall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwin on 17-02-2018.
 */

public class RecyclerViewAdapter7 extends RecyclerView.Adapter<RecyclerViewAdapter7.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter7";

    private List<model> languageselection;
    private Context mContext;

    public RecyclerViewAdapter7(List<model> languageselection, Context mContext) {
        this.languageselection = languageselection;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter7.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem6,parent,false);
        return new RecyclerViewAdapter7.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter7.ViewHolder holder, final int position){
        holder.name.setText(languageselection.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return languageselection.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        View mview;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            name = itemView.findViewById(R.id.name);
        }
    }

}
