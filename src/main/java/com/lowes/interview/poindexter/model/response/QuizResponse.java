package com.lowes.interview.poindexter.model.response;

import java.util.List;

public class QuizResponse {
    private String category;
    private List<QuestionResponse> results;

    public QuizResponse(String category, List<QuestionResponse> results) {
        this.category = category;
        this.results = results;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<QuestionResponse> getResults() {
        return results;
    }

    public void setResults(List<QuestionResponse> results) {
        this.results = results;
    }
}
