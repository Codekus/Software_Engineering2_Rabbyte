package de.hbrs.se.rabbyte.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Component
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement , Integer> {


    JobAdvertisementDTO findJobAdvertisementById(int id);
    List<JobAdvertisementDTO> findJobAdvertisementByBusinessIsNotNull();



    @Query("select j from JobAdvertisement j " +
            "where lower(j.business.businessName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(j.title) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(j.text) like lower(concat('%', :searchTerm, '%'))")
    List<JobAdvertisement> search(@Param("searchTerm") String searchTerm);
}
