package com.example.eduposts.controller;

import com.example.eduposts.dto.ProgressDto;
import com.example.eduposts.model.LearningPlan;
import com.example.eduposts.model.Progress;
import com.example.eduposts.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressController {

    private final LearningPlanService learningPlanService;

    @Autowired
    public ProgressController(LearningPlanService learningPlanService) {
        this.learningPlanService = learningPlanService;
    }

    @GetMapping("/progress")
    public ResponseEntity<List<LearningPlan>> getPlansWithProgress() {
        List<LearningPlan> plans = learningPlanService.getPlansWithProgress();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{planId}/progress")
    public ResponseEntity<ProgressDto> getPlanProgress(@PathVariable Long planId) {
        LearningPlan plan = learningPlanService.getPlanById(planId);
        if (plan == null || plan.getProgress() == null) {
            return ResponseEntity.notFound().build();
        }
        ProgressDto progressDto = convertToDto(plan.getProgress());
        return ResponseEntity.ok(progressDto);
    }

    @PutMapping("/{planId}/progress")
    public ResponseEntity<LearningPlan> updatePlanProgress(
            @PathVariable Long planId,
            @RequestBody ProgressDto progressDto) {
        LearningPlan updatedPlan = learningPlanService.updatePlanProgress(planId, progressDto);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        }
        return ResponseEntity.notFound().build();
    }

    private ProgressDto convertToDto(Progress progress) {
        ProgressDto dto = new ProgressDto();
        dto.setStatus(progress.getStatus());
        dto.setCompleted(progress.getCompleted());
        dto.setNotes(progress.getNotes());
        dto.setReminder(progress.getReminder());
        return dto;
    }
}