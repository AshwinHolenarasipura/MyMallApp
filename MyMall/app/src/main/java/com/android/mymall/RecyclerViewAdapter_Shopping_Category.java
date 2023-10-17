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

public class RecyclerViewAdapter_Shopping_Category extends RecyclerView.Adapter<RecyclerViewAdapter_Shopping_Category.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter_Cat";

    private List<model> categorylist;
    private Context mContext;

    public RecyclerViewAdapter_Shopping_Category(List<model> categorylist, Context mContext) {
        this.categorylist = categorylist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_Shopping_Category.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_category, parent, false);
        return new RecyclerViewAdapter_Shopping_Category.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Shopping_Category.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(categorylist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(categorylist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
