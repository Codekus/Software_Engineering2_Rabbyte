package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.dtos.GeneralUserDTO;
import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface GeneralUserRepository extends JpaRepository<User, Integer> {
    @Query("select g from User g where g.email = ?1")
    GeneralUserDTO findByEmail(String email);

    @Query("select g from User g where g.email = ?1 AND g.password = ?2")
    GeneralUserDTO findByEmailAndPassword(String email, String password);

    GeneralUserDTO findGeneralUserById(int nutzerid);

    @Query("select s from Student s where s.id = ?1 ")
    StudentDTO getStudent(int nutzerid);

    @Query("select b from Business b where b.id = ?1 ")
    BusinessDTO getBusiness(int nutzerid);

}
