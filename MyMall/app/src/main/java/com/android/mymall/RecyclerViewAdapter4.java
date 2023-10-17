package com.android.mymall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ashwin on 16-02-2018.
 */

public class RecyclerViewAdapter4  extends RecyclerView.Adapter<RecyclerViewAdapter4.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter4";

    private List<model> storylist;
    private Context mContext;

    public RecyclerViewAdapter4(List<model> storylist, Context mContext) {
        this.storylist = storylist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter4.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_category,parent,false);
        return new RecyclerViewAdapter4.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter4.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(storylist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(storylist.get(position).getName());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent  = new Intent(v.getContext(),DisplayStoryActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("image_url",storylist.get(position).getUrl());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storylist.size();
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
