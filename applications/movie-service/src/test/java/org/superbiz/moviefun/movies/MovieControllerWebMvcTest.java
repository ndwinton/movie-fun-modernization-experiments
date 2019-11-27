package org.superbiz.moviefun.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MovieController.class)
public class MovieControllerWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    private final static Movie MOVIE_1 = new Movie("Title 1", "Director 1", "Genre 1", 1, 2001);
    private final static Movie MOVIE_2 = new Movie("Title 2", "Director 2", "Genre 2", 2, 2002);


    @Test
    public void adding_movie_should_return_created_status() throws Exception {
        String inputJson = objectMapper.writeValueAsString(MOVIE_1);

        mockMvc.perform(post("/movies")
                .contentType("application/json")
                .content(inputJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleting_movie_should_return_no_content_status() throws Exception {
        mockMvc.perform(delete("/movies/42")).andExpect(status().isNoContent());
    }

    @Test
    public void count_with_no_parameters_counts_all() throws Exception {
        doReturn(42).when(repository).countAll();

        MvcResult result = mockMvc.perform(get("/movies/count"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is("42"));
    }

    @Test
    public void count_with_search_parameters_counts_subset() throws Exception {
        doReturn(42).when(repository).count("foo", "bar");

        MvcResult result = mockMvc.perform(get("/movies/count?field=foo&searchTerm=bar"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is("42"));
    }

    @Test
    public void paged_list_with_no_search_term_finds_all() throws Exception {
        List<Movie> expectedList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(expectedList).when(repository).findAll(100, 2);

        MvcResult result = mockMvc.perform(get("/movies?firstResult=100&maxResults=2"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    public void paged_list_with_search_term_finds_subset() throws Exception {
        List<Movie> expectedList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(expectedList).when(repository).findRange("foo", "bar", 100, 2);

        MvcResult result = mockMvc.perform(get("/movies?firstResult=100&maxResults=2&field=foo&searchTerm=bar"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    public void unpaged_list_retrieves_all() throws Exception {
        List<Movie> expectedList = Lists.newArrayList(MOVIE_1, MOVIE_2);
        doReturn(expectedList).when(repository).getMovies();

        MvcResult result = mockMvc.perform(get("/movies"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content, is(objectMapper.writeValueAsString(expectedList)));
    }
}
