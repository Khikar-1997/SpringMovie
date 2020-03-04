package com.example.demo.movie;

import java.util.Objects;

public class Movie {
    private String name;
    private String genre;
    private String duration;

    public Movie(String name, String genre, String duration) {
        this.name = name;
        if (genre.equals(String.valueOf(Genre.valueOf(genre)))){
            this.genre = genre;
        } else {
            throw new RuntimeException("!!!!!!!");
        }
        this.duration = duration;
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, duration);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
