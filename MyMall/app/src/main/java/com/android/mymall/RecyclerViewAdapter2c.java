package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ashwin on 15-02-2018.
 */

public class RecyclerViewAdapter2c extends RecyclerView.Adapter<RecyclerViewAdapter2c.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter2";

    private List<model> photolist;
    private Context mContext;

    public RecyclerViewAdapter2c(List<model> photolist, Context mContext) {
        this.photolist = photolist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter2c.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2,parent,false);
        return new RecyclerViewAdapter2c.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter2c.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(photolist.get(position).getUrl())
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(v.getContext(),PhotoDisplayActivity.class);
                intent.putExtra("image_url",photolist.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photolist.size();
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
