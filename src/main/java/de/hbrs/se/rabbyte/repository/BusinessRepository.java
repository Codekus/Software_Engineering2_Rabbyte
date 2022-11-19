package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface BusinessRepository extends JpaRepository<Business, Integer> {

    @Query("select b from Business b where b.businessName = ?1")
    public BusinessDTO findBusinessByBusinessName(String name);

    @Query("select b from Business b where b.email = ?1")
    public BusinessDTO findBusinessByBusinessEmail(String email);

    @Query("select b from Business b where b.id = ?1")
    public BusinessDTO findBusinessByBusinessID(int id);


    public BusinessDTO findBusinessById(int id);

}