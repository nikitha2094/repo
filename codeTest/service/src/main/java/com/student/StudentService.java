package com.student;

import java.util.List;

public interface StudentService {

    List<Course> retrieveCourses(int studentId);

    Student retrieveStudent(int studentId);

    void updateStudent(Student student);

}
