package com.example.ahmed.popularmoviesapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

//BuildConfig.OPEN_WEATHER_MAP_API_KEY
public class MainActivityFragment extends Fragment {
    private MovieAdapter mMovieAdapter;
    private static final String BASE_URL="http://api.themoviedb.org/3/movie/popular?api_key=";
    List<Movie> movies;
    public MainActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_view, container, false);

        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());

        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(mMovieAdapter);
        TMDBQuerry tmdbQuerry=new TMDBQuerry();
        tmdbQuerry.execute(BASE_URL+BuildConfig.Movie_MAP_API_KEY);
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
                        .append(movie.getVote_average());
                Intent intent=new Intent(getActivity(),DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,stringBuilder.toString());
                startActivity(intent);
               // Toast.makeText(getActivity(),test,Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;

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
            mMovieAdapter.clear();
            if(movies!=null||!movies.isEmpty()){
                mMovieAdapter.addAll(movies);
            }
        }
    }
}
