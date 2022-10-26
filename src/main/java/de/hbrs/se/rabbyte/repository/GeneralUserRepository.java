package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.entities.GeneralUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GeneralUserRepository extends JpaRepository<GeneralUser, Integer> {

    GeneralUserDTO findUserByEmail(String email);


    //
    GeneralUserDTO findUserById( int id);
}
