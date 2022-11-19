package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select g from Person g where g.email = ?1")
    PersonDTO findByEmail(String email);

    @Query("select g from Person g where g.email = ?1 AND g.password = ?2")
    PersonDTO findByEmailAndPassword(String email, String password);

    PersonDTO findGeneralUserById(int nutzerid);

    @Query("select s from Student s where s.id = ?1 ")
    StudentDTO getStudent(int nutzerid);

    @Query("select b from Business b where b.id = ?1 ")
    BusinessDTO getBusiness(int nutzerid);

    @Query("select g from Person g where g.email = ?1 ")
    PersonDTO findGeneralUserIdByName(String userName);

}
