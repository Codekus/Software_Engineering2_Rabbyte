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

}
