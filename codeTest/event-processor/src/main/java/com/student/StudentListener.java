package com.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StudentListener {

    @JmsListener(destination = "studentQueue")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }


}
