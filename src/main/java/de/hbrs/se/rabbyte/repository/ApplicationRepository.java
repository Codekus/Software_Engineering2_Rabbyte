package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application , Integer> {

    ApplicationDTO findJobApplicationById(int id);
}
