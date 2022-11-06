package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.RegistrationStudentDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;

public class RegistrationStudentDTOImpl extends RegistrationDTOImpl implements RegistrationStudentDTO {
    
    private StudentDTOImpl studentDTO;

    public RegistrationStudentDTOImpl(StudentDTOImpl studentDTO, String passwordFieldRepeatStudent) {
        this.setStudentDTO(studentDTO);
        this.setRepeatPassword(passwordFieldRepeatStudent);
    }

    @Override
    public StudentDTO getStudentDTO() {
        return studentDTO;
    }
    
    public void setStudentDTO(StudentDTOImpl studentDTO) {
        this.studentDTO = studentDTO;
    }
}
