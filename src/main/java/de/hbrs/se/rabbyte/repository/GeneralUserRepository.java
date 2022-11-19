package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface GeneralUserRepository extends JpaRepository<Person, Integer> {



    @Query("select g from Person g where g.email = ?1")
    GeneralUserDTO findByEmail(String email);


    GeneralUserDTO findGeneralUserById(int nutzerid);

}
