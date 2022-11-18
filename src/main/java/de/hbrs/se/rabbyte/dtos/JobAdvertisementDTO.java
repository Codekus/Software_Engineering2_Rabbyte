package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.dtos.implemented.GeneralUserDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;

public interface JobAdvertisementDTO {

    public int getId();

    public String getText();

    public String getTitle();
    public String getType();
    public Business getBusiness();

}
