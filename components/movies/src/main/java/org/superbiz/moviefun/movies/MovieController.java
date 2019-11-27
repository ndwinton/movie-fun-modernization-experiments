package org.superbiz.moviefun.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MoviesBean repository;

    public MovieController(MoviesBean repository) {

        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity addMovie(Movie movie) {
        repository.addMovie(movie);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovieId(@PathVariable long id) {
        repository.deleteMovieId(id);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count(@RequestParam(required = false) String field, @RequestParam(required = false) String searchTerm) {
        if (field != null && searchTerm != null) {
            return new ResponseEntity<>(Integer.valueOf(repository.count(field, searchTerm)), HttpStatus.OK);
        }
        return new ResponseEntity<>(Integer.valueOf(repository.countAll()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> find(@RequestParam Integer firstResult,
                                            @RequestParam Integer maxResults,
                                            @RequestParam(required = false) String field,
                                            @RequestParam(required = false) String searchTerm) {
        if (field != null && searchTerm != null) {
            return new ResponseEntity<List<Movie>>(repository.findRange(field, searchTerm, firstResult, maxResults), HttpStatus.OK);
        }
        return new ResponseEntity<List<Movie>>(repository.findAll(firstResult, maxResults), HttpStatus.OK);
    }
}
