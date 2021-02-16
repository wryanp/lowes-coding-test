package com.lowes.interview.poindexter.services.quiz;

import com.lowes.interview.poindexter.model.Question;
import com.lowes.interview.poindexter.model.response.QuestionResponse;
import com.lowes.interview.poindexter.model.response.QuizResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QuizMapper {
    public List<QuizResponse> map(Map<String, List<Question>> categoryQuestionsMaps) {
        List<QuizResponse> quizResponses = new ArrayList<>();
        categoryQuestionsMaps.forEach((category, questions) -> {
            List<QuestionResponse> questionResponses = new ArrayList<>();
            for (Question question : questions) {
                List<String> allAnswers = question.getIncorrectAnswers();
                allAnswers.add(question.getCorrectAnswer());
                questionResponses.add(new QuestionResponse(question.getType(), question.getDifficulty(), question.getQuestion(), allAnswers, question.getCorrectAnswer()));
            }
            quizResponses.add(new QuizResponse(category, questionResponses));
        });

        return quizResponses;
    }
}
