package com.lowes.interview.poindexter.integration.web;

import com.lowes.interview.poindexter.model.response.QuizResponse;
import com.lowes.interview.poindexter.services.quiz.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Api(value = "QuizResource")
@RestController
@RequestMapping("/coding/exercise/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @ApiOperation(httpMethod = "GET", value = "Get all quizzes grouped by question category", response = String.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No quizzes found"),
            @ApiResponse(code = 500, message = "There was an error fetching quizzes")
    })
    @GetMapping("")
    public ResponseEntity<Map<String, List<QuizResponse>>> getQuizzes() {
        try {
            return Optional
                    .ofNullable(Collections.singletonMap("quiz", quizService.getQuizzes()))
                    .map(quizzes -> ResponseEntity.ok().body(quizzes))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IOException | ExecutionException | InterruptedException e) {
            return null;
        }
    }
}
