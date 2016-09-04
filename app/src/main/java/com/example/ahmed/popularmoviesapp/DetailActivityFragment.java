package com.example.ahmed.popularmoviesapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailActivityFragment extends Fragment {
    private final  static String LOG_TAG=DetailActivityFragment.class.getName();
    private static final String BASE_URL="http://image.tmdb.org/t/p/w185//";

    private String poster_path;
    private String overview;
    private String release_date;
    private String original_title;
    private String vote_average;

    public DetailActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);
        Intent intent=getActivity().getIntent();
        if(intent!=null&&intent.hasExtra(Intent.EXTRA_TEXT)){
            String info =intent.getStringExtra(Intent.EXTRA_TEXT);
            ArrayList<String> A=new ArrayList<>();
            for (String retval: info.split("\\+")){
                A.add(retval);
            }

            original_title=A.get(0);
            poster_path=A.get(1);
            overview=A.get(2);
            release_date=A.get(3);
            vote_average=A.get(4);

            TextView v=(TextView)rootView.findViewById(R.id.dMovieTitle);
            v.setText(original_title);
            ImageView imageView=(ImageView)rootView.findViewById(R.id.dMoviePoster);
            Picasso.with(getContext()).load(BASE_URL+poster_path).into(imageView );
            TextView v1=(TextView)rootView.findViewById(R.id.dMovieDate);
            v1.setText(release_date);
            TextView v2=(TextView)rootView.findViewById(R.id.dMovieRate);
            v2.setText(vote_average);
            TextView v3=(TextView)rootView.findViewById(R.id.dMovieTime);
            v3.setText(release_date);
            TextView v4=(TextView)rootView.findViewById(R.id.dMovieOverview);
            v4.setText(overview);
            v4.setMovementMethod(new ScrollingMovementMethod());
            TextView v5=(TextView)rootView.findViewById(R.id.dMovieTrailer);
            v5.setText(overview);



        }
        return rootView;
    }

}
