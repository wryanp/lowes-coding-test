package com.lowes.interview.poindexter.model;

import java.util.List;

public class Questions {
    private String responseCode;
    private List<Question> results;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
