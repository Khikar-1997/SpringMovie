package com.example.demo.controller;

import com.example.demo.movie.Movie;
import com.example.demo.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MovieController {
    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "/movie")
    public void create(@RequestBody Movie movie) {
        movieService.create(movie);
    }

    @GetMapping(value = "/movie")
    public ResponseEntity<ArrayList<Movie>> selectAllMovies() {
        ArrayList<Movie> movies = movieService.selectAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id) {
        Movie movie = movieService.findMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping(value = "/movie/{id}")
    public @ResponseBody
    void update(@PathVariable int id, @RequestBody Movie movie) {
        movieService.update(movie,id);
    }

    @DeleteMapping(value = "/movie/{id}")
    public void delete(@PathVariable int id){
        movieService.delete(id);
    }
}
