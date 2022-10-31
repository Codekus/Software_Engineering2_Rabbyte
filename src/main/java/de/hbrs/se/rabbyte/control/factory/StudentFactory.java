package de.hbrs.se.rabbyte.control.factory;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Student;

public class StudentFactory {
    public static Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();

        student.setPassword(studentDTO.getPassword());
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        return student;
    }
}
