package com.example.ahmed.popularmoviesapp;

/**
 * Created by Ahmed on 9/8/2016.
 */
public class Trailer {
    private String trailerKey;
    private String trialerName;

    public Trailer(String trailerKey, String trialerName) {
        this.trailerKey = trailerKey;
        this.trialerName = trialerName;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public String getTrialerName() {
        return trialerName;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public void setTrialerName(String trialerName) {
        this.trialerName = trialerName;
    }
}
