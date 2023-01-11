package de.hbrs.se.rabbyte.repository;
import de.hbrs.se.rabbyte.entities.Business;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import de.hbrs.se.rabbyte.dtos.JobAdvertisementDTO;
import de.hbrs.se.rabbyte.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement , Integer> {


    @Query("select j from JobAdvertisement j where j.id = :ID")
    JobAdvertisementDTO findJobAdvertisementById(@Param("ID") int ID);

    List<JobAdvertisementDTO> findJobAdvertisementByBusinessIsNotNull();



    @Query("select j from JobAdvertisement j " +
            "where lower(j.business.businessName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(j.title) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(j.text) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(j.type) like lower(concat('%', :searchTerm, '%'))")
    List<JobAdvertisement> search(@Param("searchTerm") String searchTerm);


    @Query("select j from JobAdvertisement j where j.business.id = :ID")
    List<JobAdvertisement> searchByID(@Param("ID") int ID);

    @Transactional
    @Modifying
    @Query("update JobAdvertisement j set j.business = :user_id,j.title = :title, j.text = :text, j.type = :type where j.id = :ID")
    void editJobAdvert(@Param("ID") int ID, @Param("user_id") Business user_id, @Param("title") String title, @Param("text") String text, @Param("type") String type);

    @Transactional
    @Modifying
    @Query("delete from JobAdvertisement j where j.id = :ID ")
    void deleteJob(@Param("ID") int ID);
}
