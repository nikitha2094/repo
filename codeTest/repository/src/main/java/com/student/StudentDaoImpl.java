package com.student;

import com.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    private static List<Student> students = new ArrayList<>();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Student retrieveStudent(int studentId) {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE student_id = :id", Student.class);
            return student;
        } catch (Exception e) {
            //log the error
            logger.error("Exception thrown while fetching student details", e);
            throw new DaoException("Exception thrown while fetching student details");
        }
    }

    public List<Course> retrieveCourses(int studentId) {
        Student student = getStudent(studentId);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }


    public Student getStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    static {
        // Initialize Data. since we do not have db connection, i am preparing static data here
        Course course1 = new Course(1, "Spring");
        Course course2 = new Course(2, "Spring MVC");

        Student student1 = new Student("student name", 1,
                new ArrayList<>(
                        Arrays.asList(course1, course2)));

        students.add(student1);
    }

    @Override
    public int update(Student student) {
        try {
            return jdbcTemplate.update("UPDATE STUDENT set name = :name where student_id= :id", student.getName(), student.getId());
        } catch (Exception e) {
            //log the error
            logger.error("Exception thrown while updating student details", e);
            throw new DaoException("Exception thrown while updating student details");
        }
    }
}
