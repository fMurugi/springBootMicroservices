package com.fiona.Movieinfoservice.models;

public class Movie {
    String movieId;
    String movieName;

    public Movie(String movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }
    public Movie(){

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
