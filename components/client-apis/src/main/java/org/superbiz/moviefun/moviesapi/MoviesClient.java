package org.superbiz.moviefun.moviesapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${movies.url}", name = "MoviesClient")
public interface MoviesClient {

    @PostMapping("/")
    void addMovie(@RequestBody MovieInfo movie);

    @DeleteMapping("/{id}")
    void deleteMovieId(@PathVariable("id") Long id);

    @GetMapping("/count")
    int countAll();

    @GetMapping("/count")
    int count(@RequestParam("field") String field, @RequestParam("searchTerm") String searchTerm);

    @GetMapping("/")
    List<MovieInfo> findAll(@RequestParam("firstResult") int firstResult, @RequestParam("maxResults") int maxResults);

    @GetMapping("/")
    List<MovieInfo> findRange(@RequestParam("field") String field,
                              @RequestParam("searchTerm") String searchTerm,
                              @RequestParam("firstResult") int firstResult,
                              @RequestParam("maxResults") int maxResults);

    @GetMapping("/")
    List<MovieInfo> getMovies();

}