package com.lowes.interview.poindexter.services.questions;

import com.google.gson.Gson;
import com.lowes.interview.poindexter.model.Question;
import com.lowes.interview.poindexter.model.Questions;
import com.lowes.interview.poindexter.services.http.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private HttpService httpService;

    @Override
    @Async
    public CompletableFuture<ConcurrentMap<String, List<Question>>> getQuestionsByCategory(String questionsURL) throws IOException {
        String questionsJSON = httpService.get(questionsURL);

        Gson gson = new Gson();
        Questions questions = gson.fromJson(questionsJSON, Questions.class);

        return CompletableFuture.completedFuture(new QuestionsMapper().map(questions));
    }
}
