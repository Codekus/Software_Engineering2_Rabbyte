package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface GeneralUserRepository extends JpaRepository<User, Integer> {
    @Query("select g from User g where g.email = ?1")
    GeneralUserDTO findByEmail(String email);


    GeneralUserDTO findGeneralUserById(int nutzerid);

}
