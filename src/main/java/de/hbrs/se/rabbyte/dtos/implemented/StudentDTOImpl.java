package de.hbrs.se.rabbyte.dtos.implemented;

import de.hbrs.se.rabbyte.dtos.StudentDTO;



/**
 * A DTO for the {@link de.hbrs.se.rabbyte.entities.Student} entity
 */
public class StudentDTOImpl extends GeneralUserDTOImpl implements StudentDTO {

    private String faculty;
    private String firstName;
    private String lastName;

    @Override
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
