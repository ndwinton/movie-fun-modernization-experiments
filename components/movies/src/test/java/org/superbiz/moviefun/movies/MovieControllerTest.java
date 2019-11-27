package org.superbiz.moviefun.movies;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class MovieControllerTest {

    MovieRepository repository = mock(MovieRepository.class);
    MovieController controller = new MovieController(repository);

    private static final Movie MOVIE_1 = new Movie("Title 1", "Director 1", "Genre 1", 1, 2001);
    private static final Movie MOVIE_2 = new Movie("Title 2", "Director 2", "Genre 2", 2, 2002);

    @Test
    public void adding_movie_should_return_created_status() {
        Movie input = MOVIE_1;

        ResponseEntity<Movie> response = controller.addMovie(input);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        verify(repository).addMovie(input);
    }

    @Test
    public void deleting_movie_should_return_no_content_status() {

        ResponseEntity<Movie> response = controller.deleteMovieId(42L);

        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        verify(repository).deleteMovieId(42L);
    }

    @Test
    public void count_with_empty_parameters_counts_all() {
        doReturn(42).when(repository).countAll();

        ResponseEntity<Integer> response = controller.count(null, null);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(Integer.valueOf(42)));
        verify(repository).countAll();
    }

    @Test
    public void count_with_search_parameters_counts_subset() {
        doReturn(2).when(repository).count("field", "searchTerm");

        ResponseEntity<Integer> response = controller.count("field", "searchTerm");
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(Integer.valueOf(2)));
        verify(repository).count("field", "searchTerm");
    }

    @Test
    public void paged_list_with_missing_search_parameters_finds_all() {
        List<Movie> resultList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(resultList).when(repository).findAll(100, 2);

        ResponseEntity<List<Movie>> response = controller.find(100, 2, null, null);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(2));
        assertThat(response.getBody(), containsInAnyOrder(resultList.toArray()));
        verify(repository).findAll(100, 2);
    }

    @Test
    public void paged_list_with_search_parameters_finds_subset() {
        List<Movie> resultList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(resultList).when(repository).findRange("field", "searchTerm", 100, 2);

        ResponseEntity<List<Movie>> response = controller.find(100, 2, "field", "searchTerm");
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(2));
        assertThat(response.getBody(), containsInAnyOrder(resultList.toArray()));
        verify(repository).findRange("field", "searchTerm", 100, 2);
    }

    @Test
    public void unpaged_list_finds_all() {
        List<Movie> resultList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(resultList).when(repository).getMovies();

        ResponseEntity<List<Movie>> response = controller.find(null, null, null, null);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(2));
        assertThat(response.getBody(), containsInAnyOrder(resultList.toArray()));
        verify(repository).getMovies();

    }
}
