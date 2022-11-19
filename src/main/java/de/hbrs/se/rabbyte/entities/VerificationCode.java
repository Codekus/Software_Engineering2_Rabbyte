package de.hbrs.se.rabbyte.entities;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "verification_code", schema = "rabbyte")
public class VerificationCode {

    private int id;
    private Person person;
    private Date date;
    private String token;

    @Id
    @GeneratedValue(
            strategy=GenerationType.AUTO,
            generator = "verification_id"
    )
    @SequenceGenerator(
            name = "verification_id",
            sequenceName = "rabbyte.seq_verification_id",
            allocationSize=1
    )
    @Column(name = "verification_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    @OneToOne(targetEntity = Person.class,cascade = {CascadeType.MERGE} )
    @JoinColumn(name = "user_id")
    public Person getUser() {

        return person;
    }
    public void setUser(Person person) {
        this.person = person;
    }


    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}