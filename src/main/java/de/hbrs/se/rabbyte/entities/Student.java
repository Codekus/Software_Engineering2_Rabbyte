package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;

@Entity
@Table(name = "Student", schema = "rabbyte", catalog = "ihbib2s")
public class Student extends User {
;

    private String faculty;
    private String firstName;
    private String lastName;

    @Basic
    @Column(name = "fachbereich")
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    @Basic
    @Column(name = "vorname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Basic
    @Column(name = "nachname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
