package com.example.ahmed.popularmoviesapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//BuildConfig.OPEN_WEATHER_MAP_API_KEY
public class MainActivityFragment extends Fragment {
    private MovieAdapter mMovieAdapter;
    private  static String LOG_TAG=MainActivityFragment.class.getName();
    private static final String BASE_URL="http://api.themoviedb.org/3/movie/";
    private static final String KEY_QUERY="?api_key=";
    private DataBaseHandler mDataBaseHandler;
    private List<Movie> mMovies;

    private String mPrevStatus;
    private View loadingIndicator;
    private TextView mEmptyStateTextView;
    Callback callback;
    public MainActivityFragment(){
    }
      public interface Callback{
          public void onItemSelected(String uriString);
         // public void activeMovie(Movie movie);
      }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences
                (getActivity());
        String sortBy=sharedPreferences.getString(
                getString(R.string.key_sort_type),
                getString(R.string.defualt_sort_type));
        if(mPrevStatus!=sortBy){
            if(sortBy.equals("fav")) {
                loadingIndicator.setVisibility(View.GONE);
                mDataBaseHandler=new DataBaseHandler(getActivity());
                mMovieAdapter.clear();
                mMovies=mDataBaseHandler.getAllMovies();
                if(mMovies!=null||!mMovies.isEmpty()){
                    mMovieAdapter.addAll(mMovies);
                }
            }
            else{
                TMDBQuerry tmdbQuerry=new TMDBQuerry();
                StringBuilder uriString = new StringBuilder();
                uriString.append(BASE_URL).append(sortBy)
                        .append(KEY_QUERY).append(BuildConfig.Movie_MAP_API_KEY);
                tmdbQuerry.execute(uriString.toString());
            }

            if(isXLargeTablet(getActivity())) {
                StringBuilder stringBuilder = new StringBuilder();
                if(mMovieAdapter.getItem(0)!=null) {
                    Movie movies = mMovieAdapter.getItem(0);
                    stringBuilder.append(movies.getOriginal_title()).append("+")
                            .append(movies.getPoster_path()).append("+")
                            .append(movies.getOverview()).append("+")
                            .append(movies.getRelease_date()).append("+")
                            .append(movies.getVote_average()).append("+")
                            .append(movies.getId());
                    callback.onItemSelected(stringBuilder.toString());
                }
            }
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_view, container, false);
        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setItemChecked(1,true);
        gridView.setSelection(1);
        gridView.setAdapter(mMovieAdapter);
        mEmptyStateTextView=(TextView)rootView.findViewById(R.id.empty_view);
        gridView.setEmptyView(mEmptyStateTextView);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences
                (getActivity());
        String sortBy=sharedPreferences.getString(
                getString(R.string.key_sort_type),
                getString(R.string.defualt_sort_type)
        );
        loadingIndicator=(rootView).findViewById(R.id.loading_indicator);
        mPrevStatus=sortBy;
        if(sortBy.equals("fav")) {
            loadingIndicator.setVisibility(View.GONE);
            mDataBaseHandler = new DataBaseHandler(getActivity());
            mMovieAdapter.clear();
            mMovies = mDataBaseHandler.getAllMovies();
            if (mMovies != null || !mMovies.isEmpty()) {
                mMovieAdapter.addAll(mMovies);
            }
        }
        else{
            ConnectivityManager cm =
                    (ConnectivityManager)getActivity()
                            .getSystemService(getActivity().CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected) {
                TMDBQuerry tmdbQuerry = new TMDBQuerry();
                StringBuilder uriString = new StringBuilder();
                uriString.append(BASE_URL).append(sortBy)
                        .append(KEY_QUERY).append(BuildConfig.Movie_MAP_API_KEY);
                tmdbQuerry.execute(uriString.toString());
            }else {
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText("No Internet Conection");
                Toast.makeText(getActivity(), "No Internet Connection !", Toast.LENGTH_LONG).show();
            }

        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie=mMovieAdapter.getItem(position);
                String test=movie.getOriginal_title();
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(movie.getOriginal_title()).append("+")
                        .append(movie.getPoster_path()).append("+")
                        .append(movie.getOverview()).append("+")
                        .append(movie.getRelease_date()).append("+")
                        .append(movie.getVote_average()).append("+")
                        .append(movie.getId());
                ((Callback)getActivity()).onItemSelected(stringBuilder.toString());
            }
        });

           gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
////////////////////////////////////////////////////////////////////////////////////////////////////
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callback=(Callback) getActivity();
    }

    private class  TMDBQuerry extends AsyncTask<String,Void,List<Movie>>{
        @Override
        protected List<Movie> doInBackground(String... urls) {
         if(urls[0]==null||urls.length<1){
             return null;
         }
            List<Movie>movieList=QueryUtils.fetchMoviesData(urls[0]);
            return movieList;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mEmptyStateTextView.setText("No Internet");
            mMovieAdapter.clear();
            if(movies!=null||!movies.isEmpty()){
                mMovieAdapter.addAll(movies);
            }
            loadingIndicator.setVisibility(View.GONE);
            if(isXLargeTablet(getActivity())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(movies.get(0).getOriginal_title()).append("+")
                        .append(movies.get(0).getPoster_path()).append("+")
                        .append(movies.get(0).getOverview()).append("+")
                        .append(movies.get(0).getRelease_date()).append("+")
                        .append(movies.get(0).getVote_average()).append("+")
                        .append(movies.get(0).getId());
                callback.onItemSelected(stringBuilder.toString());
            }
        }

    }
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }
}
