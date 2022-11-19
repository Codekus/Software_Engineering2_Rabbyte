package de.hbrs.se.rabbyte.entities;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "business", schema = "rabbyte")
public class Business extends Person {

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
