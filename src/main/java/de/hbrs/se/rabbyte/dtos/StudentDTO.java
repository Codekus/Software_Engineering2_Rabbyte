package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.Student;

public interface StudentDTO extends PersonDTO {

    public String getFaculty();
    public String getFirstName();
    public String getLastName();
    public String getEmail();
}
