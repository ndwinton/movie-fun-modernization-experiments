package org.superbiz.moviefun.moviesapi;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(url = "${movies.url}", name = "MoviesClient")
public interface MoviesClient {

    @RequestLine("POST /")
    @Headers("Content-type: application/json")
    void addMovie(MovieInfo movie);

    @RequestLine("DELETE /{id}")
    void deleteMovieId(@Param("id") Long id);

    @RequestLine("GET /?firstResult={firstResult}&maxResults={maxResults}")
    List<MovieInfo> findAll(@Param("firstResult") int firstResult, @Param("maxResults") int maxResults);

    @RequestLine("GET /count")
    int countAll();

    @RequestLine("GET /count?field={field}&searchTerm={searchTerm}")
    int count(@Param("field") String field, @Param("searchTerm") String searchTerm);

    @RequestLine("GET /?field={field}&searchTerm={searchTerm}&firstResult={firstResult}&maxResults={maxResults}")
    List<MovieInfo> findRange(@Param("field") String field,
                              @Param("searchTerm") String searchTerm,
                              @Param("firstResult") int firstResult,
                              @Param("maxResults") int maxResults);

    @RequestLine("GET /")
    List<MovieInfo> getMovies();

}