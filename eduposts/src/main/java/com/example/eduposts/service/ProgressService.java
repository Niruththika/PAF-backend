package com.example.eduposts.service;

import com.example.eduposts.dto.ProgressDto;
import com.example.eduposts.model.LearningPlan;
import com.example.eduposts.model.Progress;
import com.example.eduposts.repository.LearningPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
    private final LearningPlanRepository learningPlanRepository;

    @Autowired
    public ProgressService(LearningPlanRepository learningPlanRepository) {
        this.learningPlanRepository = learningPlanRepository;
    }

    public LearningPlan updatePlanProgress(Long planId, ProgressDto progressDto) {
        return learningPlanRepository.findById(planId)
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
                    return learningPlanRepository.save(plan);
                })
                .orElse(null);
    }
}