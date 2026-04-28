package com.studentachievements.backend.repository;

import com.studentachievements.backend.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, String> {
    List<Achievement> findByStudentId(String studentId);
    List<Achievement> findByTeacherId(String teacherId);
    List<Achievement> findByStatus(String status);
    List<Achievement> findByStudentIdAndStatus(String studentId, String status);
    List<Achievement> findByCategory(String category);
}
