package com.lowes.interview.poindexter.services.quiz;

import com.lowes.interview.poindexter.model.Question;
import com.lowes.interview.poindexter.model.response.QuizResponse;
import com.lowes.interview.poindexter.services.questions.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@Service
public class QuizServiceImpl implements QuizService {
    private static final String CATEGORY_11_URL = "https://opentdb.com/api.php?amount=5&amp;category=11";
    private static final String CATEGORY_12_URL = "https://opentdb.com/api.php?amount=5&amp;category=12";

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuizMapper quizMapper;

    @Override
    public List<QuizResponse> getQuizzes() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<ConcurrentMap<String, List<Question>>> category11Questions = questionsService.getQuestionsByCategory(CATEGORY_11_URL);
        CompletableFuture<ConcurrentMap<String, List<Question>>> category12Questions = questionsService.getQuestionsByCategory(CATEGORY_12_URL);

        CompletableFuture.allOf(category11Questions, category12Questions).join();

        ConcurrentMap<String, List<Question>> category11QuestionsMap = category11Questions.get();
        ConcurrentMap<String, List<Question>> category12QuestionsMap = category12Questions.get();

        return quizMapper.map(mergeQuestionsMap(category11QuestionsMap, category12QuestionsMap));
    }

    private Map<String, List<Question>> mergeQuestionsMap(Map<String, List<Question>> map1, Map<String, List<Question>> map2) {
        // TODO: enhancement opportunity: convert this to a .merge() implementation using the concurrent maps (computeIfPresent)
        Map<String, List<Question>> mergedMap = new HashMap<>(map1);

        map2.forEach((key, value) -> {
            List<Question> list = mergedMap.get(key);
            if (list == null) {
                mergedMap.put(key, value);
            } else {
                List<Question> mergedValue = new ArrayList<>(value);
                mergedValue.addAll(list);
                mergedMap.put(key, mergedValue);
            }
        });

        return mergedMap;
    }
}
