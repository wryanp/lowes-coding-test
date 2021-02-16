package com.lowes.interview.poindexter.integration.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalQuizControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private QuizController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnRealJson() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/coding/exercise/quiz",
                String.class)).contains("\"quiz\":[");
    }
}
