package com.android.mymall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwin on 17-02-2018.
 */

public class RecyclerViewAdapter6 extends RecyclerView.Adapter<RecyclerViewAdapter6.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter6";

    private List<model> mainmovieoffers;
    private Context mContext;

    public RecyclerViewAdapter6(List<model> mainmovieoffers, Context mContext) {
        this.mainmovieoffers = mainmovieoffers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter6.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem5,parent,false);
        return new RecyclerViewAdapter6.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter6.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(mainmovieoffers.get(position).getUrl())
                .into(holder.image);

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainmovieoffers.size();
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
