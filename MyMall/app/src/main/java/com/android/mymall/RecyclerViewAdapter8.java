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
 * Created by Ashwin on 17-02-2018.
 */

public class RecyclerViewAdapter8 extends RecyclerView.Adapter<RecyclerViewAdapter8.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter8";
    private List<model2> mainmoviefeedoffers;
    private Context mContext;

    public RecyclerViewAdapter8(List<model2> mainmoviefeedoffers, Context mContext) {
        this.mainmoviefeedoffers = mainmoviefeedoffers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter8.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem7,parent,false);
        return new RecyclerViewAdapter8.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter8.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(mainmoviefeedoffers.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(mainmoviefeedoffers.get(position).getName());
        holder.language.setText(mainmoviefeedoffers.get(position).getLanguage());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MovieInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_url",mainmoviefeedoffers.get(position).getUrl());
                intent.putExtra("image_name",mainmoviefeedoffers.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainmoviefeedoffers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView language;

        View mview;

        public ViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            image = itemView.findViewById(R.id.listimages);
            name = itemView.findViewById(R.id.name);
            language = itemView.findViewById(R.id.languagename);

        }
    }
}
