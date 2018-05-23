package com.simonin.clement.tp2room.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.simonin.clement.tp2room.R;
import com.simonin.clement.tp2room.database.AppDatabase;
import com.simonin.clement.tp2room.database.entity.Place;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Place> mDataset;
    private final LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView placeName;
        public TextView placeDescription;
        public Button deleteButton;

        public Place place;

        public ViewHolder(View v) {
            super(v);

            placeName = v.findViewById(R.id.place_name);
            placeDescription = v.findViewById(R.id.place_description);
            deleteButton = v.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            AppDatabase.get(context).placeDao().delete(place);
            CharSequence text = context.getString(R.string.toast_delete_place_success);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 10);
            toast.show();
        }
    }

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        mDataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = mDataset.get(position);

        holder.place = place;

        holder.placeName.setText(place.getName());
        holder.placeDescription.setText(place.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateData(List<Place> places) {
        mDataset.clear();
        if (places != null) {
            mDataset.addAll(places);
        }
        notifyDataSetChanged();
    }
}