package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public StudentDTO findStudentById(int id);

    @Query("select s from Student s where s.firstName = ?1 and s.lastName = ?2")
    StudentDTO findByFirstNameAndLastName(String firstName, String lastName);

    public List<Student> findStudentsByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrEmailContainsIgnoreCaseOrCityContainsIgnoreCaseOrFacultyContainingIgnoreCase(String firstName, String lastName, String email, String city, String faculty);


}
