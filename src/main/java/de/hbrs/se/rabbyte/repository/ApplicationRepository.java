package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.ApplicationDTO;
import de.hbrs.se.rabbyte.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ApplicationRepository extends JpaRepository<Application , Integer> {

    ApplicationDTO findApplicationById(int id);
}
