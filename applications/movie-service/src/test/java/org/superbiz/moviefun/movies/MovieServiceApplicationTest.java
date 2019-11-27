package org.superbiz.moviefun.movies;

import org.hamcrest.core.IsNot;
import org.hamcrest.number.OrderingComparison;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieServiceApplication.class, webEnvironment = RANDOM_PORT)
public class MovieServiceApplicationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void server_should_start_up_correctly() {
        ResponseEntity<String> respone = restTemplate.getForEntity("/", String.class);
        assertThat(respone.getStatusCodeValue(), OrderingComparison.lessThan(500));

    }
}
