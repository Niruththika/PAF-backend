package com.example.eduposts.dto;

import java.util.List;

public class QuizDto {
    private Long planId;
    private List<QuizQuestionDto> questions;

    // Constructors
    public QuizDto() {}

    public QuizDto(Long planId, List<QuizQuestionDto> questions) {
        this.planId = planId;
        this.questions = questions;
    }

    // Getters and setters
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public List<QuizQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestionDto> questions) {
        this.questions = questions;
    }
}