package com.lowes.interview.poindexter.services.quiz;

import com.lowes.interview.poindexter.model.response.QuizResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface QuizService {
    List<QuizResponse> getQuizzes() throws IOException, ExecutionException, InterruptedException;
}
