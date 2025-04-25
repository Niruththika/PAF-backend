package com.example.eduposts.controller;

import com.example.eduposts.dto.QuizDto;
import com.example.eduposts.dto.QuizResultDto;
import com.example.eduposts.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateQuiz(@RequestBody QuizDto quizDto) {
        try {
            return ResponseEntity.ok(quizService.createOrUpdateQuiz(quizDto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<?> getQuizByPlanId(@PathVariable Long planId) {
        try {
            return ResponseEntity.ok(quizService.getQuizByPlanId(planId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/plan/{planId}")
    public ResponseEntity<Boolean> quizExistsForPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(quizService.quizExistsForPlan(planId));
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<Void> deleteQuizByPlanId(@PathVariable Long planId) {
        quizService.deleteQuizByPlanId(planId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/evaluate/{planId}")
    public ResponseEntity<?> evaluateQuiz(
            @PathVariable Long planId,
            @RequestBody List<Integer> userAnswers) {
        try {
            return ResponseEntity.ok(quizService.evaluateQuiz(planId, userAnswers));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}