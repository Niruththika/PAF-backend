package com.example.eduposts.repository;

import com.example.eduposts.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByLearningPlanId(Long planId);
    boolean existsByLearningPlanId(Long planId);
    void deleteByLearningPlanId(Long planId);
}