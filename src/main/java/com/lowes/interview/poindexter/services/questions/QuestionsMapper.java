package com.lowes.interview.poindexter.services.questions;

import com.lowes.interview.poindexter.model.Question;
import com.lowes.interview.poindexter.model.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class QuestionsMapper {
    public ConcurrentMap<String, List<Question>> map(Questions questions) {
        ConcurrentMap<String, List<Question>> categoryQuestionsMap = new ConcurrentHashMap<>();

        for (Question question : questions.getResults()) {
            if (categoryQuestionsMap.containsKey(question.getCategory())) {
                updateCategoryQuestionsMap(categoryQuestionsMap, question);
            } else {
                categoryQuestionsMap.put(question.getCategory(), new ArrayList<>(List.of(question)));
            }
        }

        return categoryQuestionsMap;
    }

    private void updateCategoryQuestionsMap(Map<String, List<Question>> categoryQuestionsMap, Question question) {
        List<Question> categoryQuestions = categoryQuestionsMap.get(question.getCategory());
        categoryQuestions.add(question);
        categoryQuestionsMap.put(question.getCategory(), categoryQuestions);
    }
}
