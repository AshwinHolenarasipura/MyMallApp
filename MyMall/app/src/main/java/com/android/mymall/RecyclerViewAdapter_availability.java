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

public class RecyclerViewAdapter_availability extends RecyclerView.Adapter<RecyclerViewAdapter_availability.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter_ava";

    private List<model> availabilitylist;
    private Context mContext;

    public RecyclerViewAdapter_availability(List<model> availabilitylist, Context mContext) {
        this.availabilitylist = availabilitylist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_availability.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_availability, parent, false);
        return new RecyclerViewAdapter_availability.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_availability.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(availabilitylist.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(availabilitylist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return availabilitylist.size();
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
