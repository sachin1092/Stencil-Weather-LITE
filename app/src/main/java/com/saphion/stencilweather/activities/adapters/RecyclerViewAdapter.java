package com.saphion.stencilweather.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saphion.stencilweather.R;
import com.saphion.stencilweather.activities.activities.MainActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleViewHolder> {

    private final Activity mActivity;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView listIndicator;
        ImageView myLocation;
        View container;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvLocationName);
            listIndicator = (ImageView)itemView.findViewById(R.id.ivListIndicator);
            myLocation = (ImageView)itemView.findViewById(R.id.ivMyLocation);
            container = itemView.findViewById(R.id.locationItemContainer);
        }

    }

    private Context mContext;
    private ArrayList<String> mDataset;

    public void setDataList(ArrayList<String> mDataset){
        this.mDataset = (ArrayList<String>) mDataset.clone();
    }

    public ArrayList<String> getArrayList(){
        return this.mDataset;
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> objects, ArrayList<String> subItems, Activity mActivity) {
        this.mContext = context;
        this.mDataset = objects;
        this.mActivity = mActivity;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        String item = mDataset.get(position);

        if(position == 0){
            viewHolder.myLocation.setVisibility(View.VISIBLE);
        } else {
            viewHolder.myLocation.setVisibility(View.GONE);
        }

        if(position == mDataset.size() - 1){
            viewHolder.listIndicator.setImageResource(R.drawable.end);
        } else {
            viewHolder.listIndicator.setImageResource(R.drawable.middle);
        }

        viewHolder.name.setText(item);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mActivity).setViewPagerPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}