package com.studentachievements.backend.service;

import com.studentachievements.backend.dto.LoginRequest;
import com.studentachievements.backend.dto.LoginResponse;
import com.studentachievements.backend.model.User;
import com.studentachievements.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getEmail()));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new LoginResponse(
                user.getId(), user.getEmail(), user.getName(), user.getRole(),
                user.getCourse(), user.getDepartment(), user.getYear(),
                user.getTeacherId(), "Login successful"
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing.setRole(updatedUser.getRole());
        existing.setCourse(updatedUser.getCourse());
        existing.setDepartment(updatedUser.getDepartment());
        existing.setYear(updatedUser.getYear());
        existing.setTeacherId(updatedUser.getTeacherId());
        return userRepository.save(existing);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getStudentsByTeacher(String teacherId) {
        return userRepository.findByTeacherId(teacherId);
    }

    /** Assigns a teacher to a student */
    public User assignTeacher(String studentId, String teacherId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
        student.setTeacherId(teacherId);
        return userRepository.save(student);
    }
}
