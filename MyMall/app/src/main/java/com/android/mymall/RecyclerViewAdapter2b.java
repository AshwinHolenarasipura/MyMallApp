package com.android.mymall;

import android.content.Context;
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

public class RecyclerViewAdapter2b extends RecyclerView.Adapter<RecyclerViewAdapter2b.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter2";

    private List<model> entertainmentoffers;
    private Context mContext;

    public RecyclerViewAdapter2b(List<model> entertainmentoffers, Context mContext) {
        this.entertainmentoffers = entertainmentoffers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter2b.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2,parent,false);
        return new RecyclerViewAdapter2b.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter2b.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(entertainmentoffers.get(position).getUrl())
                .into(holder.image);

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return entertainmentoffers.size();
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
