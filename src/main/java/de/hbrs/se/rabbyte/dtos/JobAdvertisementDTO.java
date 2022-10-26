package de.hbrs.se.rabbyte.dtos;

import de.hbrs.se.rabbyte.entities.Business;

public interface JobAdvertisementDTO {

    public int getId();

    public String getText();

    public String getTitle();

    public Business getBusiness();

}
