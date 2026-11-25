
package com.scd.studentrecords;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Display all students or search by name
    @GetMapping
    public String getAllStudents(
            @RequestParam(required = false) String name,
            Model model) {

        model.addAttribute("students",
                studentService.searchStudents(name));

        model.addAttribute("searchName", name);

        return "students";
    }

    // 2. Display one student's details
    @GetMapping("/{regNo}")
    public String getStudentDetails(
            @PathVariable String regNo,
            Model model) {

        Student student = studentService.getStudentByRegNo(regNo);

        if (student == null) {
            model.addAttribute("error", "Student not found.");
        }

        model.addAttribute("student", student);

        return "student-details";
    }

    // 3. Open a blank student form
    @GetMapping("/new")
    public String showNewStudentForm(Model model) {

        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add New Student");

        return "student-form";
    }

    // 4. Save a new student
    @PostMapping
    public String addStudent(
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {

        try {
            studentService.addStudent(student);
            redirectAttributes.addFlashAttribute(
                    "success", "Student added successfully.");

            return "redirect:/students";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "error", e.getMessage());

            return "redirect:/students/new";
        }
    }

    // 5. Open the edit form with existing student information
    @GetMapping("/{regNo}/edit")
    public String showEditStudentForm(
            @PathVariable String regNo,
            Model model) {

        Student student = studentService.getStudentByRegNo(regNo);

        if (student == null) {
            model.addAttribute("error", "Student not found.");
            return "student-details";
        }

        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Edit Student");

        return "student-form";
    }

    // 6. Update student information
    @PostMapping("/{regNo}/update")
    public String updateStudent(
            @PathVariable String regNo,
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {

        try {
            studentService.updateStudent(regNo, student);

            redirectAttributes.addFlashAttribute(
                    "success", "Student updated successfully.");

            return "redirect:/students";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "error", e.getMessage());

            return "redirect:/students/" + regNo + "/edit";
        }
    }

    // 7. Delete a student
    @PostMapping("/{regNo}/delete")
    public String deleteStudent(
            @PathVariable String regNo,
            RedirectAttributes redirectAttributes) {

        try {
            studentService.deleteStudent(regNo);

            redirectAttributes.addFlashAttribute(
                    "success", "Student deleted successfully.");

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(
                    "error", e.getMessage());
        }

        return "redirect:/students";
    }
}
