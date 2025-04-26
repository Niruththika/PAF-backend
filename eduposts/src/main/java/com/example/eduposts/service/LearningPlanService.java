package com.example.eduposts.service;

import com.example.eduposts.dto.LearningPlanDto;
import com.example.eduposts.dto.ProgressDto;
import com.example.eduposts.model.LearningPlan;
import com.example.eduposts.model.Progress;
import com.example.eduposts.model.Quiz;
import com.example.eduposts.repository.LearningPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningPlanService {
    private final LearningPlanRepository repository;

    @Autowired
    public LearningPlanService(LearningPlanRepository repository) {
        this.repository = repository;
    }

    public LearningPlan createPlan(LearningPlanDto planDto) {
        LearningPlan plan = new LearningPlan(
                planDto.getTitle(),
                planDto.getSubject(),
                planDto.getTopics(),
                planDto.getResources(),
                planDto.getDate(),
                planDto.isSavePlan()
        );

        if (planDto.getProgress() != null) {
            ProgressDto progressDto = planDto.getProgress();
            Progress progress = new Progress();
            progress.setStatus(progressDto.getStatus());
            progress.setCompleted(progressDto.getCompleted());
            progress.setNotes(progressDto.getNotes());
            progress.setReminder(progressDto.getReminder());
            plan.setProgress(progress);
        }

        return repository.save(plan);
    }

    public List<LearningPlan> getAllPlans() {
        return repository.findAll();
    }

    public LearningPlan getPlanById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LearningPlan updatePlan(Long id, LearningPlanDto planDto) {
        return repository.findById(id)
                .map(plan -> {
                    plan.setTitle(planDto.getTitle());
                    plan.setSubject(planDto.getSubject());
                    plan.setTopics(planDto.getTopics());
                    plan.setResources(planDto.getResources());
                    plan.setDate(planDto.getDate());
                    plan.setSaved(planDto.isSavePlan());

                    if (planDto.getProgress() != null) {
                        ProgressDto progressDto = planDto.getProgress();
                        Progress progress = plan.getProgress();
                        if (progress == null) {
                            progress = new Progress();
                        }
                        progress.setStatus(progressDto.getStatus());
                        progress.setCompleted(progressDto.getCompleted());
                        progress.setNotes(progressDto.getNotes());
                        progress.setReminder(progressDto.getReminder());
                        plan.setProgress(progress);
                    }

                    return repository.save(plan);
                })
                .orElse(null);
    }

    public void deletePlan(Long id) {
        repository.deleteById(id);
    }

    public LearningPlan updatePlanProgress(Long id, ProgressDto progressDto) {
        return repository.findById(id)
                .map(plan -> {
                    Progress progress = plan.getProgress();
                    if (progress == null) {
                        progress = new Progress();
                    }
                    progress.setStatus(progressDto.getStatus());
                    progress.setCompleted(progressDto.getCompleted());
                    progress.setNotes(progressDto.getNotes());
                    progress.setReminder(progressDto.getReminder());
                    plan.setProgress(progress);
                    return repository.save(plan);
                })
                .orElse(null);
    }

    public LearningPlan resetPlanProgress(Long planId) {
        return repository.findById(planId)
                .map(plan -> {
                    Progress progress = new Progress();
                    progress.setStatus("Not Started");
                    progress.setCompleted(0);
                    progress.setNotes("");
                    progress.setReminder(null);
                    plan.setProgress(progress);
                    return repository.save(plan);
                })
                .orElse(null);
    }

    public List<LearningPlan> getSavedPlans() {
        return repository.findBySaved(true);
    }

    public List<LearningPlan> getPlansWithProgress() {
        return repository.findAll().stream()
                .filter(plan -> plan.getProgress() != null)
                .collect(Collectors.toList());
    }

    public List<LearningPlan> getPlansWithoutProgress() {
        return repository.findAll().stream()
                .filter(plan -> plan.getProgress() == null)
                .collect(Collectors.toList());
    }
}