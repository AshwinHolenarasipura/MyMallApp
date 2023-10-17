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

import java.util.List;

/**
 * Created by Ashwin on 14-02-2018.
 */

public class RecyclerViewAdapter_c extends RecyclerView.Adapter<RecyclerViewAdapter_c.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter";

    private List<model> cinemalist;
    private Context mContext;

    public RecyclerViewAdapter_c(List<model> cinemalist, Context mContext) {
        this.cinemalist = cinemalist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_c.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        return new RecyclerViewAdapter_c.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_c.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(cinemalist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(cinemalist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,EntertainmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cinemalist.size();
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

