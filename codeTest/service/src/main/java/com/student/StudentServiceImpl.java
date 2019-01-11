package com.student;

import com.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentDao studentDao;

    @Autowired
    JmsTemplate jmsTemplate;


    public Student retrieveStudent(int studentId) {
        try {
            return studentDao.retrieveStudent(studentId);
        } catch (Exception e) {
            //log the exception
            logger.error("Service Exception thrown while fetching student details", e);
            throw new ServiceException("service exception while retrieving student info");
        }
    }

    public List<Course> retrieveCourses(int studentId) {
        try {
            return studentDao.retrieveCourses(studentId);
        } catch (Exception e) {
            //log the exception
            logger.error("Service Exception thrown while fetching student courses", e);
            throw new ServiceException("service exception while retrieving student courses info");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStudent(Student student) {
        try {
            studentDao.update(student);
            jmsTemplate.convertAndSend("studentQueue", "Saved student successfully");
        } catch (Exception e) {
            //log the exception
            logger.error("Service Exception thrown while updating student details", e);
            throw new ServiceException("service exception while updating student details");
        }
    }
}
