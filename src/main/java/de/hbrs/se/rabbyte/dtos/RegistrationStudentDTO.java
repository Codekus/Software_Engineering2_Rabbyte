package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.dtos.RegistrationDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;

public interface RegistrationStudentDTO extends RegistrationDTO {


    public StudentDTO getStudentDTO();
}
