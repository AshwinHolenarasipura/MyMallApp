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

import java.util.List;

/**
 * Created by Ashwin on 15-02-2018.
 */

public class RecyclerViewAdapter_Moviebuzz extends RecyclerView.Adapter<RecyclerViewAdapter_Moviebuzz.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter_Mov";

    private List<model> moviebuzzfeed;
    private Context mContext;

    public RecyclerViewAdapter_Moviebuzz(List<model> moviebuzzfeed, Context mContext) {
        this.moviebuzzfeed = moviebuzzfeed;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter_Moviebuzz.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_buzz, parent, false);
        return new RecyclerViewAdapter_Moviebuzz.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter_Moviebuzz.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(moviebuzzfeed.get(position).getUrl())
                .into(holder.image);
        holder.name.setText(moviebuzzfeed.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,WebPageDisplayActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("moviebuzzfeed_id",moviebuzzfeed.get(position).userId);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return moviebuzzfeed.size();
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
