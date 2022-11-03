package de.hbrs.se.rabbyte.entities;


import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "business", schema = "rabbyte")
public class Business extends User {

    private String businessName;

    @Basic
    @Column(name = "unternehmensname", nullable = false, length = 30)
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String unternehmensname) {
        this.businessName = unternehmensname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Business business = (Business) o;

        return businessName.equals(business.businessName);
    }

    @Override
    public int hashCode() {
        return Objects.hash( businessName);
    }
}
