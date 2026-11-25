
package com.scd.studentrecords;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Search students by name
    public List<Student> searchStudents(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllStudents();
        }

        return studentRepository.searchByName(name.trim());
    }

    // Get student by registration number
    public Student getStudentByRegNo(String regNo) {
        return studentRepository.findByRegNo(regNo);
    }

    // Add new student
    public void addStudent(Student student) {

        validateStudent(student);

        if (studentRepository.existsByRegNo(student.getRegNo().trim())) {
            throw new IllegalArgumentException(
                "A student with this registration number already exists."
            );
        }

        student.setRegNo(student.getRegNo().trim());
        student.setName(student.getName().trim());
        student.setDepartment(student.getDepartment().trim());

        studentRepository.insert(student);
    }

    // Update existing student
    public void updateStudent(String regNo, Student student) {

        Student existingStudent = studentRepository.findByRegNo(regNo);

        if (existingStudent == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        validateStudent(student);

        student.setRegNo(regNo);
        student.setName(student.getName().trim());
        student.setDepartment(student.getDepartment().trim());

        studentRepository.update(student);
    }

    // Delete student
    public void deleteStudent(String regNo) {

        Student existingStudent = studentRepository.findByRegNo(regNo);

        if (existingStudent == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        studentRepository.deleteByRegNo(regNo);
    }

    // Validate student information
    private void validateStudent(Student student) {

        if (student.getRegNo() == null ||
            student.getRegNo().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Registration number is required."
            );
        }

        if (student.getName() == null ||
            student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Student name is required."
            );
        }

        if (student.getDepartment() == null ||
            student.getDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Department is required."
            );
        }

        if (student.getCgpa() == null ||
            student.getCgpa() < 0 ||
            student.getCgpa() > 4.00) {
            throw new IllegalArgumentException(
                "CGPA must be between 0.00 and 4.00."
            );
        }
    }
}
