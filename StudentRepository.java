
package com.scd.studentrecords;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Maps database rows into Student objects
    private final RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();

        student.setId(rs.getInt("id"));
        student.setRegNo(rs.getString("reg_no"));
        student.setName(rs.getString("name"));
        student.setDepartment(rs.getString("department"));
        student.setCgpa(rs.getDouble("cgpa"));

        return student;
    };

    // Get all students
    public List<Student> findAll() {
        String sql = "SELECT * FROM students ORDER BY name";
        return jdbc.query(sql, rowMapper);
    }

    // Search students by name
    public List<Student> searchByName(String name) {
        String sql = "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";

        return jdbc.query(sql, rowMapper, "%" + name + "%");
    }

    // Find one student by registration number
    public Student findByRegNo(String regNo) {
        String sql = "SELECT * FROM students WHERE reg_no = ?";

        List<Student> students = jdbc.query(sql, rowMapper, regNo);

        if (students.isEmpty()) {
            return null;
        }

        return students.get(0);
    }

    // Check whether registration number already exists
    public boolean existsByRegNo(String regNo) {
        String sql = "SELECT COUNT(*) FROM students WHERE reg_no = ?";

        Integer count = jdbc.queryForObject(sql, Integer.class, regNo);

        return count != null && count > 0;
    }

    // Insert a new student
    public void insert(Student student) {
        String sql = "INSERT INTO students (reg_no, name, department, cgpa) VALUES (?, ?, ?, ?)";

        jdbc.update(
            sql,
            student.getRegNo(),
            student.getName(),
            student.getDepartment(),
            student.getCgpa()
        );
    }

    // Update existing student
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, department = ?, cgpa = ? WHERE reg_no = ?";

        jdbc.update(
            sql,
            student.getName(),
            student.getDepartment(),
            student.getCgpa(),
            student.getRegNo()
        );
    }

    // Delete student
    public void deleteByRegNo(String regNo) {
        String sql = "DELETE FROM students WHERE reg_no = ?";

        jdbc.update(sql, regNo);
    }
}
