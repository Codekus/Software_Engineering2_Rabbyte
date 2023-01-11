package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface StudentRepository extends JpaRepository<Student, Integer> {

    StudentDTO findStudentById(int id);

    @Query("select s from Student s where s.firstName = ?1 and s.lastName = ?2")
    StudentDTO findByFirstNameAndLastName(String firstName, String lastName);
    public List<Student> findStudentsByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrEmailContainsIgnoreCaseOrCityContainsIgnoreCaseOrFacultyContainingIgnoreCase(String firstName, String lastName, String email, String city, String faculty);

    @Transactional
    @Modifying
    @Query("update Student j set j.email = :email, j.firstName = :firstname, j.lastName = :lastname, j.faculty = :faculty, j.password = :password where j.id = :ID")
    void editStudent(@Param("ID") int ID, @Param("email") String email, @Param("password") String password, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("faculty") String faculty);

}
