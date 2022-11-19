package de.hbrs.se.rabbyte.dtos;

public interface PersonDTO {

    public int getId();
    public String getEmail();
    public String getPassword();
    public int getPlz();
    public String getCity();
    public String getCountry();
    public String getStreet();
    public String getStreetNumber();
    public boolean getEnabled();
    public String getSalt();
}
