-- Student Achievements — seed data
-- This runs automatically when spring.sql.init.mode=always
-- (JPA ddl-auto=update creates the tables first)

-- Clear existing data
DELETE FROM achievements;
DELETE FROM users;

-- ─── ADMIN ───────────────────────────────────────────────────────────────────
INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('801c2145-a914-41a8-b20c-30f84e000001', 'admin1@gmail.com', 'admin1', 'password123', 'admin', NULL, NULL, NULL, NULL);

-- ─── TEACHERS ────────────────────────────────────────────────────────────────
INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('t001-0000-0000-0000-000000000001', 'teacher1@gmail.com', 'Dr. Priya Sharma', 'password123', 'teacher', NULL, 'Computer Science', NULL, NULL);

INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('t002-0000-0000-0000-000000000002', 'teacher2@gmail.com', 'Prof. Ravi Kumar', 'password123', 'teacher', NULL, 'Electronics', NULL, NULL);

-- ─── STUDENTS ────────────────────────────────────────────────────────────────
INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('a5422332-1d33-44cc-b9f7-cee8f7800001', 'student1@gmail.com', 'student1', 'password123', 'student', 'B.Tech Computer Science', 'Computer Science', '1st Year', 't001-0000-0000-0000-000000000001');

INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('s002-0000-0000-0000-000000000002', 'harsha@gmail.com', 'Harsha M', 'password123', 'student', 'B.Tech Computer Science', 'Computer Science', '2nd Year', 't001-0000-0000-0000-000000000001');

INSERT INTO users (id, email, name, password, role, course, department, year, teacher_id)
VALUES ('s003-0000-0000-0000-000000000003', 'ananya@gmail.com', 'Ananya Reddy', 'password123', 'student', 'B.Tech Electronics', 'Electronics', '1st Year', 't002-0000-0000-0000-000000000002');

-- ─── ACHIEVEMENTS ────────────────────────────────────────────────────────────
INSERT INTO achievements (id, title, description, category, type, level, status, student_id, student_name, teacher_id, proof_url, date, submitted_at, reviewed_at, feedback)
VALUES (
  'ach001-0000-0000-0000-000000000001',
  'Inter-College Hackathon 1st Place',
  'Won first place in the state-level inter-college hackathon organized by VIT University.',
  'Technical', 'Award', 'state', 'Approved',
  'a5422332-1d33-44cc-b9f7-cee8f7800001', 'student1',
  't001-0000-0000-0000-000000000001',
  NULL,
  '2024-11-15',
  '2024-11-16 10:00:00', '2024-11-17 14:00:00',
  'Excellent work! Keep it up.'
);

INSERT INTO achievements (id, title, description, category, type, level, status, student_id, student_name, teacher_id, proof_url, date, submitted_at, reviewed_at, feedback)
VALUES (
  'ach002-0000-0000-0000-000000000002',
  'National Debate Championship',
  'Participated in national-level debate competition representing the college.',
  'Cultural', 'Participation', 'national', 'Approved',
  's002-0000-0000-0000-000000000002', 'Harsha M',
  't001-0000-0000-0000-000000000001',
  NULL,
  '2024-12-01',
  '2024-12-02 09:00:00', '2024-12-03 11:00:00',
  'Well done on representing the college!'
);

INSERT INTO achievements (id, title, description, category, type, level, status, student_id, student_name, teacher_id, proof_url, date, submitted_at, reviewed_at, feedback)
VALUES (
  'ach003-0000-0000-0000-000000000003',
  'State Athletics Gold Medal',
  'Won gold in 100m sprint at state athletics meet.',
  'Sports', 'Award', 'state', 'Pending',
  's003-0000-0000-0000-000000000003', 'Ananya Reddy',
  't002-0000-0000-0000-000000000002',
  NULL,
  '2025-01-20',
  '2025-01-21 08:00:00', NULL,
  NULL
);
