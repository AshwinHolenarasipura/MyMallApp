package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwin on 16-02-2018.
 */

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter3";

    private List<model> cinemaoffers;
    private Context mContext;

    public RecyclerViewAdapter3(List<model> cinemaoffers, Context mContext) {
        this.cinemaoffers = cinemaoffers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem3,parent,false);
        return new RecyclerViewAdapter3.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter3.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(cinemaoffers.get(position).getUrl())
                .into(holder.image);

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MovieActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cinemaoffers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        View mview;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            image = itemView.findViewById(R.id.listimages);
        }
    }
}
