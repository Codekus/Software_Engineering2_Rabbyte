package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "nutzer", schema = "rabbyte")
public class GeneralUser {

    private int id;
    private String email;
    private String password;
    private String plz;
    private String city;
    private String country;
    private String street;
    private int streetNumber;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "nutzer_id"
    )
    @SequenceGenerator(
            name = "nutzer_id",
            sequenceName = "rabbyte.seq_nutzer_id",
            allocationSize=1
    )
    @Column(name = "nutzer_id")
    public int getId() {
        return id;
    }

    public GeneralUser setId(int id) {
        this.id = id;
        return this;
    }
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public GeneralUser setEmail(String email) {
        this.email = email;
        return this;
    }
    @Basic
    @Column(name = "passwort")
    public String getPassword() {
        return password;
    }

    public GeneralUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Basic
    @Column(name = "plz")
    public String getPlz() {
        return plz;
    }

    public GeneralUser setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    @Basic
    @Column(name = "stadt")
    public String getCity() {
        return city;
    }

    public GeneralUser setCity(String city) {
        this.city = city;
        return this;
    }
    @Basic
    @Column(name = "land")
    public String getCountry() {
        return country;
    }

    public GeneralUser setCountry(String state) {
        this.country = state;
        return this;
    }
    @Basic
    @Column(name = "strasse")
    public String getStreet() {
        return street;
    }

    public GeneralUser setStreet(String street) {
        this.street = street;
        return this;
    }
    @Basic
    @Column(name = "hausnummer")
    public int getStreetNumber() {
        return streetNumber;
    }

    public GeneralUser setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }
}
