package com.studentachievements.backend.controller;

import com.studentachievements.backend.model.User;
import com.studentachievements.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /api/users/students  — used by frontend fetchAll()
    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(userService.getUsersByRole("student"));
    }

    // GET /api/users/teachers  — used by frontend fetchAll()
    @GetMapping("/teachers")
    public ResponseEntity<List<User>> getAllTeachers() {
        return ResponseEntity.ok(userService.getUsersByRole("teacher"));
    }

    // GET /api/users/role/{role}
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/users/teacher/{teacherId}/students
    @GetMapping("/teacher/{teacherId}/students")
    public ResponseEntity<List<User>> getStudentsByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(userService.getStudentsByTeacher(teacherId));
    }

    // PUT /api/users/students/{studentId}/assign  — assigns teacher to student
    @PutMapping("/students/{studentId}/assign")
    public ResponseEntity<User> assignTeacher(
            @PathVariable String studentId,
            @RequestBody Map<String, String> body) {
        String teacherId = body.get("teacherId");
        return ResponseEntity.ok(userService.assignTeacher(studentId, teacherId));
    }
}
