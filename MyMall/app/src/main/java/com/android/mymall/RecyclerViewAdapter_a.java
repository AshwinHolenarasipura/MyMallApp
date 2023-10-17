package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ashwin on 14-02-2018.
 */

public class RecyclerViewAdapter_a extends RecyclerView.Adapter<RecyclerViewAdapter_a.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter";

    private List<model> shoppinglist;
    private Context mContext;
    private String shopping_id;

    public RecyclerViewAdapter_a(List<model> shoppinglist, Context mContext) {
        this.shoppinglist = shoppinglist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_a.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        return new RecyclerViewAdapter_a.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_a.ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(shoppinglist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(shoppinglist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(v.getContext(),ShoppingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_url",shoppinglist.get(position).getUrl());
                intent.putExtra("image_name",shoppinglist.get(position).getName());
                intent.putExtra("shopping_id",shoppinglist.get(position).userId);
                intent.putExtra("mall_id",shoppinglist.get(position).getMall_id());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppinglist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
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

