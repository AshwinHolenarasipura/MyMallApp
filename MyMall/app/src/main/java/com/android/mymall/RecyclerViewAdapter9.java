package com.android.mymall;

import android.content.Context;
import android.content.Intent;
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
 * Created by Ashwin on 20-02-2018.
 */

public class RecyclerViewAdapter9 extends RecyclerView.Adapter<RecyclerViewAdapter9.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter9";

    private List<model1> mainlist;
    private Context mContext;

    public RecyclerViewAdapter9(List<model1> mainlist, Context mContext){
        this.mainlist = mainlist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter9.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem8,parent,false);
        return new RecyclerViewAdapter9.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter9.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(mainlist.get(position).getMallurl())
                .into(holder.image);
        holder.name.setText(mainlist.get(position).getMallname());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mall_name",mainlist.get(position).getMallname());
                intent.putExtra("mall_id",mainlist.get(position).userId);
                mContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mainlist.size();
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
