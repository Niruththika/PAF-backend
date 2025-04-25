package com.example.eduposts.dto;

import java.util.List;

public class QuizResultDto {
    private Long quizId;
    private int score;
    private int totalQuestions;
    private List<Integer> userAnswers;

    // Constructors
    public QuizResultDto() {}

    public QuizResultDto(Long quizId, int score, int totalQuestions, List<Integer> userAnswers) {
        this.quizId = quizId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.userAnswers = userAnswers;
    }

    // Getters and setters
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}