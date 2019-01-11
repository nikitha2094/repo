package com.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{studentId}/courses")
    public List<Course> retrieveCoursesForStudent(@PathVariable int studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @GetMapping("/students/{studentId}")
    public Student retrieveStudent(@PathVariable int studentId) {
        return studentService.retrieveStudent(studentId);
    }

    @PostMapping("/students/update")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }



}
