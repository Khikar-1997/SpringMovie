package com.example.demo.movie;

import java.util.Objects;

public class Movie {
    private String name;
    private String genre;
    private String duretion;

    public Movie(String name, String genre, String duretion) {
        this.name = name;
        if (genre.equals(String.valueOf(Genre.valueOf(genre)))){
            this.genre = genre;
        } else {
            throw new RuntimeException("!!!!!!!");
        }
        this.duretion = duretion;
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

    public String getDuretion() {
        return duretion;
    }

    public void setDuretion(String duretion) {
        this.duretion = duretion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(duretion, movie.duretion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, duretion);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duretion='" + duretion + '\'' +
                '}';
    }
}
