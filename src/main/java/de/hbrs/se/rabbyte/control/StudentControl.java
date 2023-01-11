package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentControl {

    @Autowired
    private StudentRepository studentRepository;
    public void editStudent(StudentDTO studentDTO){

        this.studentRepository.editStudent(
                studentDTO.getEmail(),
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getFaculty()
        );
    }
}
