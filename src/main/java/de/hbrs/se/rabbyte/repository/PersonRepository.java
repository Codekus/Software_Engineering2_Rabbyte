package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.PersonDTO;
import de.hbrs.se.rabbyte.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends JpaRepository<Person, Integer> {



    @Query("select g from Person g where g.email = ?1")
    PersonDTO findByEmail(String email);


    PersonDTO findPersonById(int nutzerid);

}
