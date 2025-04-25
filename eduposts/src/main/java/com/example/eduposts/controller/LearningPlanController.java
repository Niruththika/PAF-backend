//package com.example.eduposts.controller;
//
//import com.example.eduposts.dto.LearningPlanDto;
//import com.example.eduposts.model.LearningPlan;
//import com.example.eduposts.service.LearningPlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/plans")
//@CrossOrigin(origins = "http://localhost:3000") // Adjust to match your frontend URL
//public class LearningPlanController {
//    private final LearningPlanService service;
//
//    @Autowired
//    public LearningPlanController(LearningPlanService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ResponseEntity<LearningPlan> createPlan(@RequestBody LearningPlanDto planDto) {
//        try {
//            LearningPlan createdPlan = service.createPlan(planDto);
//            return ResponseEntity.ok(createdPlan);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<LearningPlan>> getAllPlans() {
//        List<LearningPlan> plans = service.getAllPlans();
//        return ResponseEntity.ok(plans);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<LearningPlan> getPlanById(@PathVariable Long id) {
//        LearningPlan plan = service.getPlanById(id);
//        if (plan != null) {
//            return ResponseEntity.ok(plan);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<LearningPlan> updatePlan(@PathVariable Long id, @RequestBody LearningPlanDto planDto) {
//        LearningPlan updatedPlan = service.updatePlan(id, planDto);
//        if (updatedPlan != null) {
//            return ResponseEntity.ok(updatedPlan);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
//        service.deletePlan(id);
//        return ResponseEntity.noContent().build();
//    }
//}
package com.example.eduposts.controller;

import com.example.eduposts.dto.LearningPlanDto;
import com.example.eduposts.dto.ProgressDto;
import com.example.eduposts.model.LearningPlan;
import com.example.eduposts.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin(origins = "http://localhost:3000")
public class LearningPlanController {
    private final LearningPlanService service;

    @Autowired
    public LearningPlanController(LearningPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LearningPlan> createPlan(@RequestBody LearningPlanDto planDto) {
        try {
            LearningPlan createdPlan = service.createPlan(planDto);
            return ResponseEntity.ok(createdPlan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LearningPlan>> getAllPlans() {
        List<LearningPlan> plans = service.getAllPlans();
        return ResponseEntity.ok(plans);
    }
    @GetMapping("/with-progress")
    public ResponseEntity<List<LearningPlan>> getPlansWithProgress() {
        List<LearningPlan> plans = service.getPlansWithProgress();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPlan> getPlanById(@PathVariable Long id) {
        LearningPlan plan = service.getPlanById(id);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPlan> updatePlan(@PathVariable Long id, @RequestBody LearningPlanDto planDto) {
        LearningPlan updatedPlan = service.updatePlan(id, planDto);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        service.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/progress")
    public ResponseEntity<LearningPlan> updatePlanProgress(
            @PathVariable Long id,
            @RequestBody ProgressDto progressDto) {
        LearningPlan updatedPlan = service.updatePlanProgress(id, progressDto);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/saved")
    public ResponseEntity<List<LearningPlan>> getSavedPlans() {
        List<LearningPlan> plans = service.getSavedPlans();
        return ResponseEntity.ok(plans);
    }

}