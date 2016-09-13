package com.example.ahmed.popularmoviesapp;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailActivityFragment extends Fragment {
    private final  static String LOG_TAG=DetailActivityFragment.class.getName();
    private static final String BASE_URL="http://image.tmdb.org/t/p/w185//";
     final static String DETAIL_URI="URI";
    private final static String BASE_INFO="http://api.themoviedb.org/3/movie/";
    private DataBaseHandler mDataBaseHandler;
    private  FloatingActionButton fab;
    private ArrayList<Reviews>mReviewsArrayList=new ArrayList<>();


    private String poster_path;
    private String overview;
    private String release_date;
    private String original_title;
    private String vote_average;
    private String id;
    private TrailerAdabter mTrailerAdabter;
   private ReviewsAdapter mReviewsAdapter;
    public DetailActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);

        Bundle args=getArguments();
        if(args!=null){

            String info =args.getString(DetailActivityFragment.DETAIL_URI);
            ArrayList<String> movieInfo=new ArrayList<>();
            for (String retval: info.split("\\+")){
                movieInfo.add(retval);
        }

            original_title=movieInfo.get(0);
            poster_path=movieInfo.get(1);
            overview=movieInfo.get(2);
            release_date=movieInfo.get(3);
            vote_average=movieInfo.get(4);
            id=movieInfo.get(5);

            TextView titleView=(TextView)rootView.findViewById(R.id.dMovieTitle);
            titleView.setText(original_title);
            ImageView posterView=(ImageView)rootView.findViewById(R.id.dMoviePoster);
            Picasso.with(getContext()).load(BASE_URL+poster_path).into(posterView );
            TextView dateView=(TextView)rootView.findViewById(R.id.dMovieDate);
            dateView.setText(release_date);
            TextView rateView=(TextView)rootView.findViewById(R.id.dMovieRate);
            rateView.setText(vote_average+"/10");
            //durationView.setText(release_date.substring(0,4));
            TextView overviewView=(TextView)rootView.findViewById(R.id.dMovieOverview);
            overviewView.setText(overview);
            overviewView.setMovementMethod(new ScrollingMovementMethod());

              mDataBaseHandler=new DataBaseHandler(getActivity());

             fab=(FloatingActionButton)rootView.findViewById(R.id.fab);
            if (mDataBaseHandler.isFavorite(id)) {
                fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.fav));
            } else {
                fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.un_fav));
            }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDataBaseHandler.isFavorite(id)) {
                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.un_fav));
                        Toast.makeText(getActivity(), "Removed From Favorite list", Toast.LENGTH_SHORT).show();
                        mDataBaseHandler.removeMovie(id);

                    } else {
                        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.fav));
                        Toast.makeText(getActivity(), "Add From Favorite list", Toast.LENGTH_SHORT).show();
                        mDataBaseHandler.addMovie(id, poster_path, release_date, vote_average, overview, original_title);
                    }


                }
            });


        }
        mTrailerAdabter=new TrailerAdabter(getActivity(),  new ArrayList<Trailer>());
        ListView trailerListView=(ListView)rootView.findViewById(R.id.trailer_list);
        trailerListView.setAdapter(mTrailerAdabter);

        ListView reviewsListView=(ListView)rootView.findViewById(R.id.review_list);
        mReviewsAdapter=new ReviewsAdapter(getContext(), new ArrayList<Reviews>());
        reviewsListView.setAdapter(mReviewsAdapter);

        TrailerQuerry trailerQuerry=new TrailerQuerry();
        if(id!=null ) {
            trailerQuerry.execute(BASE_INFO + id + "/videos?api_key=" + BuildConfig.Movie_MAP_API_KEY,
                    BASE_INFO + id + "/reviews?api_key=" + BuildConfig.Movie_MAP_API_KEY);
        }
        trailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                String youtubeUrl="http://www.youtube.com/watch?v="
                        +mTrailerAdabter.getItem(position).getTrailerKey();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)));

            }
        });
        return rootView;
    }

    private class  TrailerQuerry extends AsyncTask<String,Void,List<Trailer>> {
        @Override
        protected List<Trailer> doInBackground(String... urls) {
            if(urls[0]==null||urls.length<1){
                return null;
            }
            List<Trailer>trailerList=QueryUtils.fetchTrailerData(urls[0]);
            mReviewsArrayList=(ArrayList<Reviews>) QueryUtils.fetchReviewsData(urls[1]);
            return trailerList;
        }

        @Override
        protected void onPostExecute(List<Trailer>trailers ) {
            mTrailerAdabter.clear();
            mReviewsAdapter.clear();
            if(trailers!=null||!trailers.isEmpty()){
                mTrailerAdabter.addAll(trailers);
            }
            if(mReviewsArrayList!=null||!mReviewsArrayList.isEmpty()){
                mReviewsAdapter.addAll(mReviewsArrayList);
            }
        }
    }

}
