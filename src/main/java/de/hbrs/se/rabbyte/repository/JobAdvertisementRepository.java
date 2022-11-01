package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement , Integer> {


    List<JobAdvertisementDTO> findJobAdvertisementByIdIsNotNull();
    JobAdvertisementDTO findJobAdvertisementById(int id);


    @Query("select j from JobAdvertisement j " +
            "where lower(j.business) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(j.title) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(j.text) like lower(concat('%', :searchTerm, '%'))")


    List<JobAdvertisement> search(@Param("searchTerm") String searchTerm);
}
