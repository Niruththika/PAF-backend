package com.example.eduposts.service;

import com.example.eduposts.dto.QuizDto;
import com.example.eduposts.dto.QuizQuestionDto;
import com.example.eduposts.dto.QuizResultDto;
import com.example.eduposts.model.*;
import com.example.eduposts.repository.LearningPlanRepository;
import com.example.eduposts.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final LearningPlanRepository learningPlanRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, LearningPlanRepository learningPlanRepository) {
        this.quizRepository = quizRepository;
        this.learningPlanRepository = learningPlanRepository;
    }

    @Transactional
    public Quiz createOrUpdateQuiz(QuizDto quizDto) {
        LearningPlan plan = learningPlanRepository.findById(quizDto.getPlanId())
                .orElseThrow(() -> new RuntimeException("Learning plan not found"));

        // Convert DTOs to entities
        List<QuizQuestion> questions = quizDto.getQuestions().stream()
                .map(dto -> new QuizQuestion(
                        dto.getQuestion(),
                        dto.getOption1(),
                        dto.getOption2(),
                        dto.getOption3(),
                        dto.getOption4(),
                        dto.getCorrectAnswer()
                ))
                .collect(Collectors.toList());

        // Check if quiz already exists for this plan
        return quizRepository.findByLearningPlanId(quizDto.getPlanId())
                .map(existingQuiz -> {
                    existingQuiz.setQuestions(questions);
                    return quizRepository.save(existingQuiz);
                })
                .orElseGet(() -> {
                    Quiz newQuiz = new Quiz(plan, questions);
                    return quizRepository.save(newQuiz);
                });
    }

    public Quiz getQuizByPlanId(Long planId) {
        return quizRepository.findByLearningPlanId(planId)
                .orElseThrow(() -> new RuntimeException("Quiz not found for this plan"));
    }

    public boolean quizExistsForPlan(Long planId) {
        return quizRepository.existsByLearningPlanId(planId);
    }

    @Transactional
    public void deleteQuizByPlanId(Long planId) {
        quizRepository.deleteByLearningPlanId(planId);
    }

    public QuizResultDto evaluateQuiz(Long planId, List<Integer> userAnswers) {
        Quiz quiz = getQuizByPlanId(planId);

        if (userAnswers.size() != quiz.getQuestions().size()) {
            throw new RuntimeException("Number of answers doesn't match number of questions");
        }

        int score = 0;
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            if (userAnswers.get(i) == quiz.getQuestions().get(i).getCorrectAnswer()) {
                score++;
            }
        }

        return new QuizResultDto(
                quiz.getId(),
                score,
                quiz.getQuestions().size(),
                userAnswers
        );
    }
}