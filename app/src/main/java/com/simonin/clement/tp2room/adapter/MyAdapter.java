package com.simonin.clement.tp2room.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simonin.clement.tp2room.R;
import com.simonin.clement.tp2room.database.entity.Place;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Place> mDataset;
    private final LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView placeName;
        public TextView placeDescription;
        public TextView placePhone;

        public ViewHolder(View v) {
            super(v);

            placeName = v.findViewById(R.id.place_name);
            placeDescription = v.findViewById(R.id.place_description);
            placePhone= v.findViewById(R.id.place_phone);

        }
    }

    public MyAdapter(Context context, List<Place> myDataset) {
        inflater = LayoutInflater.from(context);
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(mDataset.get(position).getName());
        holder.placeDescription.setText(mDataset.get(position).getDescription());
        holder.placePhone.setText(mDataset.get(position).getPhone());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}