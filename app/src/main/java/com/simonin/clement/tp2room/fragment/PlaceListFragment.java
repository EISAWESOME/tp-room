package com.simonin.clement.tp2room.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simonin.clement.tp2room.R;
import com.simonin.clement.tp2room.adapter.MyAdapter;
import com.simonin.clement.tp2room.database.AppDatabase;
import com.simonin.clement.tp2room.database.entity.Place;

import java.util.List;


public class PlaceListFragment extends Fragment implements Observer<List<Place>>{

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Au clic d'un item, on ouvre la map, et on zoom sur le point en question, avec son nom afficher

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_place_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        fetchDbDatas();

        return rootView;
    }

    public void fetchDbDatas() {
         AppDatabase.get(getContext()).placeDao().subscribeAll().observe(this, this);
    }

    @Override
    public void onChanged(@Nullable List<Place> places) {
        mAdapter.updateData(places);
    }
}
