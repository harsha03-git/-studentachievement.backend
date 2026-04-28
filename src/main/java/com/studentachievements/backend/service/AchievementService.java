package com.studentachievements.backend.service;

import com.studentachievements.backend.model.Achievement;
import com.studentachievements.backend.repository.AchievementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public Achievement submit(Achievement achievement) {
        achievement.setStatus("Pending");
        achievement.setSubmittedAt(LocalDateTime.now());
        return achievementRepository.save(achievement);
    }

    public List<Achievement> getAll() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getByStudent(String studentId) {
        return achievementRepository.findByStudentId(studentId);
    }

    public List<Achievement> getByTeacher(String teacherId) {
        return achievementRepository.findByTeacherId(teacherId);
    }

    public List<Achievement> getByStatus(String status) {
        return achievementRepository.findByStatus(status);
    }

    public Optional<Achievement> getById(String id) {
        return achievementRepository.findById(id);
    }

    /**
     * Teacher review: set status (Approved/Rejected) + feedback
     */
    public Achievement review(String id, String status, String feedback, String teacherId) {
        Achievement a = findOrThrow(id);
        a.setStatus(status);
        a.setFeedback(feedback);
        a.setTeacherId(teacherId);
        a.setReviewedAt(LocalDateTime.now());
        return achievementRepository.save(a);
    }

    /**
     * Update only the status — used by admin or teacher quick-action
     */
    public Achievement updateStatus(String id, String status) {
        Achievement a = findOrThrow(id);
        a.setStatus(status);
        a.setReviewedAt(LocalDateTime.now());
        return achievementRepository.save(a);
    }

    /**
     * Add or update feedback text
     */
    public Achievement addFeedback(String id, String feedback) {
        Achievement a = findOrThrow(id);
        a.setFeedback(feedback);
        a.setReviewedAt(LocalDateTime.now());
        return achievementRepository.save(a);
    }

    public void delete(String id) {
        achievementRepository.deleteById(id);
    }

    public Achievement update(String id, Achievement updated) {
        Achievement existing = findOrThrow(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setCategory(updated.getCategory());
        existing.setType(updated.getType());
        existing.setLevel(updated.getLevel());
        existing.setDate(updated.getDate());
        existing.setProofUrl(updated.getProofUrl());
        return achievementRepository.save(existing);
    }

    private Achievement findOrThrow(String id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found: " + id));
    }
}
