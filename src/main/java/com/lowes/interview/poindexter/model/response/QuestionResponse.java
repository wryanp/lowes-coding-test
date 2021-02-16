package com.lowes.interview.poindexter.model.response;

import java.util.List;

public class QuestionResponse {
    private String type;
    private String difficulty;
    private String question;
    private List<String> allAnswers;
    private String correctAnswer;

    public QuestionResponse(String type, String difficulty, String question, List<String> allAnswers, String correctAnswer) {
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.allAnswers = allAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(List<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
