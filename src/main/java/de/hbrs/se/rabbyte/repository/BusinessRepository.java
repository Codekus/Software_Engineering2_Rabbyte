package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;
import de.hbrs.se.rabbyte.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BusinessRepository extends JpaRepository<Business, Integer> {

    public BusinessDTO findBusinessByBusinessName(String name);

    public BusinessDTO findBusinessById(int id);
}