package com.studentachievements.backend.controller;

import com.studentachievements.backend.model.Achievement;
import com.studentachievements.backend.service.AchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    // GET /api/achievements
    @GetMapping
    public ResponseEntity<List<Achievement>> getAll() {
        return ResponseEntity.ok(achievementService.getAll());
    }

    // GET /api/achievements/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getById(@PathVariable String id) {
        return achievementService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/achievements/student/{studentId}
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Achievement>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(achievementService.getByStudent(studentId));
    }

    // GET /api/achievements/teacher/{teacherId}
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Achievement>> getByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(achievementService.getByTeacher(teacherId));
    }

    // GET /api/achievements/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Achievement>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(achievementService.getByStatus(status));
    }

    // POST /api/achievements
    @PostMapping
    public ResponseEntity<Achievement> submit(@RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.submit(achievement));
    }

    // PUT /api/achievements/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Achievement> update(@PathVariable String id, @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.update(id, achievement));
    }

    // PUT /api/achievements/{id}/review  — teacher review (approve/reject)
    @PutMapping("/{id}/review")
    public ResponseEntity<Achievement> review(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(achievementService.review(
                id,
                body.get("status"),
                body.get("feedback"),
                body.get("teacherId")
        ));
    }

    // PUT /api/achievements/{id}/status  — used by frontend updateAchievementStatus()
    @PutMapping("/{id}/status")
    public ResponseEntity<Achievement> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(achievementService.updateStatus(id, body.get("status")));
    }

    // PUT /api/achievements/{id}/feedback  — used by frontend addFeedback()
    @PutMapping("/{id}/feedback")
    public ResponseEntity<Achievement> addFeedback(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(achievementService.addFeedback(id, body.get("feedback")));
    }

    // DELETE /api/achievements/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        achievementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
