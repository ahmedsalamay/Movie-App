package com.example.ahmed.popularmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ahmed on 9/8/2016.
 */
public class TrailerAdabter extends ArrayAdapter<Trailer> {
    private final static String IMAGE_YOUTUBE_PATH="http://img.youtube.com/vi/";
    private final static String AFTER_KEY_PATH="/mqdefault.jpg";

    public TrailerAdabter(Context context, List<Trailer> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trailer trailer=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.trailer_item,parent,false);
        }
        ImageView imageView=(ImageView)convertView.findViewById(R.id.trailer_image);
        TextView textView=(TextView)convertView.findViewById(R.id.trailer_name);
        Picasso.with(getContext()).load(IMAGE_YOUTUBE_PATH+trailer.getTrailerKey()+AFTER_KEY_PATH)
        .into(imageView);
        textView.setText(trailer.getTrialerName());
        return convertView;
    }
}
