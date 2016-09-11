package com.example.ahmed.popularmoviesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {
    public static final String LOG_TAG=QueryUtils.class.getName();

    private QueryUtils(){
    }
    public static List<Movie> fetchMoviesData(String urlString){
        URL url=createUrl(urlString);
        String jsonResponse="";
        try {
            jsonResponse= makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"problem in make http request",e);
        }
        List<Movie>movieList=extractMovies(jsonResponse);
        return movieList;
    }
    public static List<Trailer> fetchTrailerData(String urlString){
        URL url=createUrl(urlString);
        String jsonResponse="";
        try{
            jsonResponse=makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"problem in make http request",e);
        }
        List<Trailer>trailerList=extractTrailers(jsonResponse);
        return trailerList;
    }


    public static List<Reviews> fetchReviewsData(String urlString){
        URL url=createUrl(urlString);
        String jsonResponse="";
        try{
            jsonResponse=makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"problem in make http request",e);
        }
        List<Reviews>reviewses=extractReviews(jsonResponse);
        return reviewses;
    }

    private static ArrayList<Movie> extractMovies(String jsonResponse){
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Movie>movies=new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try{
            JSONObject baseJsonResponse =new JSONObject(jsonResponse);
            JSONArray moviesJsonArray =(JSONArray) baseJsonResponse.get("results");
            for(int i=0;i<moviesJsonArray.length();i++){
                JSONObject currentMovie =moviesJsonArray.getJSONObject(i);
                String path=currentMovie.getString("poster_path");
                String overview=currentMovie.getString("overview");;
                String relase_date=currentMovie.getString("release_date");;
                String title=currentMovie.getString("original_title");;
                String rate=currentMovie.getString("vote_average");;
                String id=currentMovie.getString("id");
                Movie movie=new Movie(title,overview,path,relase_date,rate,id);
                movies.add(movie);
            }
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
        }catch (JSONException e){
            Log.e("QueryUtil","Problem Parcing Json",e);
        }
        return movies;
    }
    private static ArrayList<Trailer>extractTrailers(String jsonResponse){
        ArrayList<Trailer> trailers=new ArrayList<>();
        try{
            JSONObject baseJsonResonse=new JSONObject(jsonResponse);
            JSONArray jsonArrayTrailer=(JSONArray)baseJsonResonse.get("results");
            for(int i=0;i<jsonArrayTrailer.length();i++){
                JSONObject currentTrailer =jsonArrayTrailer.getJSONObject(i);
                Trailer trailer=new Trailer(currentTrailer.getString("key")
                        ,currentTrailer.getString("name"));
                trailers.add(trailer);
            }
        }catch (JSONException e){
            Log.e("QueryUtil","Problem Parcing Json",e);
        }
        return trailers;
    }
    private  static  ArrayList<Reviews>extractReviews(String jsonResponse){
        ArrayList<Reviews>reviewsArrayList=new ArrayList<>();
        try{
            JSONObject baseJsonResonse=new JSONObject(jsonResponse);
            JSONArray jsonArrayReviews=(JSONArray)baseJsonResonse.get("results");
            for(int i=0;i<jsonArrayReviews.length();i++){
                JSONObject currentReviews =jsonArrayReviews.getJSONObject(i);
                Reviews reviews=new Reviews(currentReviews.getString("author")
                        ,currentReviews.getString("content"),currentReviews.getString("url"));
                reviewsArrayList.add(reviews);
            }
        }catch (JSONException e){
            Log.e("QueryUtil","Problem Parcing Json",e);
        }
        return reviewsArrayList;

    }
    private static String makeHttpRequest(URL url)throws IOException{
        String jsonResponse="";
        InputStream inputStream=null;
        HttpURLConnection urlConnection=null;
        if(url ==null){
            return jsonResponse;
        }
        try {
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readFromSream(inputStream);
            }
            else{
                Log.e(LOG_TAG,"Problem in site response" +urlConnection.getResponseCode()+"\n URL: "+url.toString());
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem retrive json",e);
        }
        finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromSream(InputStream inputStream)throws IOException{
          StringBuilder stringBuilder=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=
                    new InputStreamReader(inputStream,
                            Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String line=reader.readLine();
            while (line!=null){
                stringBuilder.append(line);
                line=reader.readLine();
            }
        }
        return stringBuilder.toString();
    }
    private static URL createUrl(String urlString){
        URL url=null;
        try {
            url=new URL(urlString);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Problem building URL:"+urlString,e);
        }
        return url;
    }
}
