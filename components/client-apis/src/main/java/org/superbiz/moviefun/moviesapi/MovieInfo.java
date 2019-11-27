package org.superbiz.moviefun.moviesapi;

public class MovieInfo {
    private final long id;
    private final String director;
    private final String title;
    private final String genre;
    private final int rating;
    private final int year;

    public MovieInfo(long id, String director, String title, String genre, int rating, int year) {
        this.id = id;
        this.director = director;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
    }

    public MovieInfo(String director, String title, String genre,  int rating, int year) {
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
