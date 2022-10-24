package de.hbrs.se.rabbyte.entities;


import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Unternehmen", schema = "rabbyte")
public class Business extends GeneralUser {

    private String unternehmensname;

    @Basic
    @Column(name = "unternehmensname", nullable = false, length = 30)
    public String getUnternehmensname() {
        return unternehmensname;
    }

    public void setUnternehmensname(String unternehmensname) {
        this.unternehmensname = unternehmensname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Business business = (Business) o;

        return unternehmensname.equals(business.unternehmensname);
    }

    @Override
    public int hashCode() {
        return Objects.hash( unternehmensname);
    }
}
