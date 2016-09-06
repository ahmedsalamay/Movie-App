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
            ArrayList<String> movieInfo=new ArrayList<>();
            for (String retval: info.split("\\+")){
                movieInfo.add(retval);
            }

            original_title=movieInfo.get(0);
            poster_path=movieInfo.get(1);
            overview=movieInfo.get(2);
            release_date=movieInfo.get(3);
            vote_average=movieInfo.get(4);

            TextView titleView=(TextView)rootView.findViewById(R.id.dMovieTitle);
            titleView.setText(original_title);
            ImageView posterView=(ImageView)rootView.findViewById(R.id.dMoviePoster);
            Picasso.with(getContext()).load(BASE_URL+poster_path).into(posterView );
            TextView dateView=(TextView)rootView.findViewById(R.id.dMovieDate);
            dateView.setText(release_date);
            TextView rateView=(TextView)rootView.findViewById(R.id.dMovieRate);
            rateView.setText(vote_average+"/10");
            TextView durationView=(TextView)rootView.findViewById(R.id.dMovieTime);
            durationView.setText(release_date.substring(0,4));
            TextView overviewView=(TextView)rootView.findViewById(R.id.dMovieOverview);
            overviewView.setText(overview);
            overviewView.setMovementMethod(new ScrollingMovementMethod());
            TextView trailerView=(TextView)rootView.findViewById(R.id.dMovieTrailer);
            trailerView.setText(overview);



        }
        return rootView;
    }

}
