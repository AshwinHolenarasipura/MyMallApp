package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwin on 16-02-2018.
 */

public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter5";

    private List<model> cinemaoffers;
    private Context mContext;

    public RecyclerViewAdapter5(List<model> cinemaoffers, Context mContext){
        this.cinemaoffers = cinemaoffers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter5.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem4,parent,false);
        return new RecyclerViewAdapter5.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter5.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(cinemaoffers.get(position).getUrl())
                .into(holder.image);

//        holder.flipper.addView(holder.image);
//        holder.flipper.setFlipInterval(5000);
//        holder.flipper.setAutoStart(true);
//
//        holder.flipper.setInAnimation(mContext,android.R.anim.slide_in_left);
//        holder.flipper.setOutAnimation(mContext,android.R.anim.slide_out_right);

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cinemaoffers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

//        ViewFlipper flipper;

        View mview;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
//            flipper = itemView.findViewById(R.id.viewflipper);

            image = itemView.findViewById(R.id.Image);
        }
    }
}
