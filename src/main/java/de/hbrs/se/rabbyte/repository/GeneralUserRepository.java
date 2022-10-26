package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.GeneralUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GeneralUserRepository extends JpaRepository<GeneralUser, Integer> {
    @Query("select g from GeneralUser g where g.email = ?1")
    GeneralUserDTO findByEmail(String email);


    GeneralUserDTO findGeneralUserById(int nutzerid);

}
