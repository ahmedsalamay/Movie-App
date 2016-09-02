package com.example.ahmed.popularmoviesapp;

public class Movie {

    /**
     * poster_path : /e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg
     * adult : false
     * overview : From DC Comics comes the Suicide Squad, an antihero team of incarcerated
     * supervillains who act as deniable assets for the United States government,
     * undertaking high-risk black ops missions in exchange for commuted prison sentences.
     * release_date : 2016-08-03
     * genre_ids : [14,28,80]
     * id : 297761
     * original_title : Suicide Squad
     * original_language : en
     * title : Suicide Squad
     * backdrop_path : /ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg
     * popularity : 39.573181
     * vote_count : 1590
     * video : false
     * vote_average : 5.92
     */
    private String poster_path;
    private int path;
    private String overview;
    private String release_date;
    private int id;
    private String original_title;
    private double vote_average;

    public Movie(String original_title, String overview, String poster_path, String release_date, double vote_average) {
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public Movie(int path) {
        this.path =path;
    }

    public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }
    }
