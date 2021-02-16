package com.lowes.interview.poindexter.services.questions;

import com.lowes.interview.poindexter.model.Question;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;

public interface QuestionsService {
    CompletableFuture<ConcurrentMap<String, List<Question>>> getQuestionsByCategory(String questionsJSON) throws IOException;
}
