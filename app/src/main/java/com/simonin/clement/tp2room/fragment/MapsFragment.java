package com.simonin.clement.tp2room.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.simonin.clement.tp2room.R;
import com.simonin.clement.tp2room.database.AppDatabase;
import com.simonin.clement.tp2room.database.entity.Place;

import java.util.List;

// import com.google.android.gms.maps.CameraUpdateFactory;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        //map.setMyLocationEnabled(true);
        // Ca fait crash ce truc


        CameraPosition dCam = new CameraPosition.Builder().zoom(6).target(new LatLng(46.428545, 2.410924)).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(dCam));

        // Recupere les places dans la bdd
        List<Place> data = AppDatabase.get(getContext()).placeDao().getAll();
        // Ajoute les marqueur sur la map
        for (Place p : data) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(p.getLatitude(), p.getLongitude()))
                    .title(p.getName()));
        }


    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}