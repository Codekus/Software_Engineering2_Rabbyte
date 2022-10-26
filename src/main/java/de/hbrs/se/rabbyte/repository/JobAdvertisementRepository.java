package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement , Integer> {

    JobAdvertisementDTO findJobAdvertisementById(int id);
}
