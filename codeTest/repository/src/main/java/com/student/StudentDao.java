package com.student;

import java.util.List;

public interface StudentDao{

    Student retrieveStudent(int studentId);

    List<Course> retrieveCourses(int studentId);

    int update(Student student);

}
