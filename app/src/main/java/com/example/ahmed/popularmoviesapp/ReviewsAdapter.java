package com.example.ahmed.popularmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 9/9/2016.
 */
public class ReviewsAdapter extends ArrayAdapter<Reviews>{
    public ReviewsAdapter(Context context, ArrayList <Reviews> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reviews reviews=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.reviews_item,parent,false);
        }
        TextView author=(TextView) convertView.findViewById(R.id.author);
        author.setText(reviews.getAuthor());
        TextView content=(TextView)convertView.findViewById(R.id.content);
        content.setText(reviews.getContent());
        TextView url =(TextView)convertView.findViewById(R.id.url);
        url.setText(reviews.getUrl());
        return convertView;
    }
}
