package com.example.ahmed.popularmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG=MovieAdapter.class.getName();
    public MovieAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.movies_item, parent, false);
        }
        ImageView poster=(ImageView)convertView.findViewById(R.id.movie_image);
        poster.setImageResource(movie.path);
        return convertView
    }
}
