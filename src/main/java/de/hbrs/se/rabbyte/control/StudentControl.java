package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentControl {

    @Autowired
    private StudentRepository studentRepository;

}
