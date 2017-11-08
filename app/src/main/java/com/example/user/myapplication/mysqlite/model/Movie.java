package com.example.user.myapplication.mysqlite.model;

/**
 * Created by user on 07/11/17.
 */

public class Movie {

    private int id;
    private String movieTitle;


    public Movie() {
    }

    public Movie(String movieTitle) {
//        this.id = id;
        this.movieTitle = movieTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
