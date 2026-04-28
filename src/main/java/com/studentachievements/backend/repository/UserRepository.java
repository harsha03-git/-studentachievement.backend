package com.studentachievements.backend.repository;

import com.studentachievements.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
    List<User> findByTeacherId(String teacherId);
    boolean existsByEmail(String email);
}
