package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", schema = "rabbyte")
public class User {

    private int id;
    private String email;
    private String password;
    private int plz;
    private String city;
    private String country;
    private String street;
    private String streetNumber;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "user_id"
    )
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "rabbyte.seq_nutzer_id",
            allocationSize=1
    )
    @Column(name = "user_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }
    @Basic
    @Column(name = "passwort")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    @Basic
    @Column(name = "plz")
    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;

    }

    @Basic
    @Column(name = "stadt")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "land")
    public String getCountry() {
        return country;
    }

    public void setCountry(String state) {
        this.country = state;

    }
    @Basic
    @Column(name = "strasse")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;

    }
    @Basic
    @Column(name = "hausnummer")
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;

    }
}
