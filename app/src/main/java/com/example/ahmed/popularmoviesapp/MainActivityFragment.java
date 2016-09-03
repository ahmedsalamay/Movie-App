package com.example.ahmed.popularmoviesapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

//BuildConfig.OPEN_WEATHER_MAP_API_KEY
public class MainActivityFragment extends Fragment {
    private MovieAdapter mMovieAdapter;
  /*  Movie [] movies= {
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
     };*/
    ArrayList<Movie> movies=QueryUtils.extractMovies();
    public MainActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_view, container, false);

        mMovieAdapter = new MovieAdapter(getActivity(), movies);

        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(mMovieAdapter);

        return rootView;
    }
}
