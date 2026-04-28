package com.studentachievements.backend.dto;

public class LoginResponse {
    private String id;
    private String email;
    private String name;
    private String role;
    private String course;
    private String department;
    private String year;
    private String teacherId;
    private String message;

    public LoginResponse() {}

    public LoginResponse(String id, String email, String name, String role,
                         String course, String department, String year,
                         String teacherId, String message) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.course = course;
        this.department = department;
        this.year = year;
        this.teacherId = teacherId;
        this.message = message;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
