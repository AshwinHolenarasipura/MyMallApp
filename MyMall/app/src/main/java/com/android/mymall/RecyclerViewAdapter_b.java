package com.android.mymall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ashwin on 14-02-2018.
 */

public class RecyclerViewAdapter_b extends RecyclerView.Adapter<RecyclerViewAdapter_b.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter";

    private List<model> foodlist;
    private Context mContext;

    public RecyclerViewAdapter_b(List<model> foodlist, Context mContext) {
        this.foodlist = foodlist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_b.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        return new RecyclerViewAdapter_b.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter_b.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(foodlist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(foodlist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(v.getContext(),FoodActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_url",foodlist.get(position).getUrl());
                intent.putExtra("image_name",foodlist.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
       final ImageView image;
        TextView name;

        View mview;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            image = itemView.findViewById(R.id.listimages);
            name = itemView.findViewById(R.id.name);
        }
    }
}
