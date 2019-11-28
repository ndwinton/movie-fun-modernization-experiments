package org.superbiz.moviefun.moviesapi;

public class MovieInfo {
    private long id;
    private String director;
    private String title;
    private String genre;
    private int rating;
    private int year;

    public MovieInfo(long id, String director, String title, String genre, int rating, int year) {
        this.id = id;
        this.director = director;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
    }

    public MovieInfo(String title, String director, String genre,  int rating, int year) {
        this.id = 0;
        this.director = director;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
    }

    public MovieInfo() {
        this.id = 0;
        this.director = null;
        this.title = null;
        this.genre = null;
        this.rating = 0;
        this.year = 0;

    }

    public long getId() {
        return id;
    }

    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }
}
