package com.android.mymall;

import android.content.Context;
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
 * Created by Ashwin on 15-02-2018.
 */

public class RecyclerViewAdapter_Foodbuzz extends RecyclerView.Adapter<RecyclerViewAdapter_Foodbuzz.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter_Foo";

    private List<model> foodbuzzfeed;
    private Context mContext;

    public RecyclerViewAdapter_Foodbuzz(List<model> foodbuzzfeed, Context mContext) {
        this.foodbuzzfeed = foodbuzzfeed;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_Foodbuzz.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_buzz1,parent,false);
        return new RecyclerViewAdapter_Foodbuzz.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter_Foodbuzz.ViewHolder holder, final int position){
        Glide.with(mContext)
                .asBitmap()
                .load(foodbuzzfeed.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(foodbuzzfeed.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"clicked buzz story", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodbuzzfeed.size();
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
