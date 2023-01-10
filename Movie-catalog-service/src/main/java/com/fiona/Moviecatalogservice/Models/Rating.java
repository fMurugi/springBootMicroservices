package com.fiona.Moviecatalogservice.Models;

public class Rating {
    private String movieId;

    private int rating;

    public Rating(String name, int rating) {
        this.movieId = name;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String name) {
        this.movieId = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
