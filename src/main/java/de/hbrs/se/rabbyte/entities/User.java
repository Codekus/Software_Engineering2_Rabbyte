package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Nutzer", schema = "rabbyte", catalog = "ihbib2s")
public class User {

    private int id;
    private String email;
    private String password;
    private String plz;
    private String city;
    private String state;
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

    public User setId(int id) {
        this.id = id;
        return this;
    }
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    @Basic
    @Column(name = "passwort")
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Basic
    @Column(name = "plz")
    public String getPlz() {
        return plz;
    }

    public User setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    @Basic
    @Column(name = "stadt")
    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }
    @Basic
    @Column(name = "land")
    public String getLand() {
        return state;
    }

    public User setLand(String state) {
        this.state = state;
        return this;
    }
    @Basic
    @Column(name = "strasse")
    public String getStreet() {
        return street;
    }

    public User setStreet(String street) {
        this.street = street;
        return this;
    }
    @Basic
    @Column(name = "hausnummer")
    public int getStreetNumber() {
        return streetNumber;
    }

    public User setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }
}
