package com.example.ahmed.popularmoviesapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {
    Movie [] movie= {
    new Movie(R.drawable.cupcake),
    new Movie( R.drawable.donut),
    new Movie( R.drawable.eclair),
    new Movie( R.drawable.froyo),
    new Movie( R.drawable.gingerbread),
    new Movie( R.drawable.honeycomb),
    new Movie( R.drawable.icecream),
    new Movie( R.drawable.jellybean),
    new Movie( R.drawable.kitkat),
    new Movie( R.drawable.lollipop)
     };
    public MainActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.grid_view,container,false);

        return rootView;
    }
}
