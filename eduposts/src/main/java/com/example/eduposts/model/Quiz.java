package com.example.eduposts.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "plan_id", nullable = false, unique = true)
    private LearningPlan learningPlan;

    @ElementCollection
    @CollectionTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<QuizQuestion> questions;

    // Constructors
    public Quiz() {}

    public Quiz(LearningPlan learningPlan, List<QuizQuestion> questions) {
        this.learningPlan = learningPlan;
        this.questions = questions;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LearningPlan getLearningPlan() {
        return learningPlan;
    }

    public void setLearningPlan(LearningPlan learningPlan) {
        this.learningPlan = learningPlan;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}