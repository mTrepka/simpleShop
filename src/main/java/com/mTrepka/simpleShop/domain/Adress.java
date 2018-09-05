package com.mTrepka.simpleShop.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Adress {
    @Column(name = "adress_id")
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String postalAdress;
    @NotNull
    private String homeNumber;
    @NotNull
    private String street1line;
    @NotNull
    private String street2line;
    @OneToOne(targetEntity = User.class)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalAdress() {
        return postalAdress;
    }

    public void setPostalAdress(String postalAdress) {
        this.postalAdress = postalAdress;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getStreet1line() {
        return street1line;
    }

    public void setStreet1line(String street1line) {
        this.street1line = street1line;
    }

    public String getStreet2line() {
        return street2line;
    }

    public void setStreet2line(String street2line) {
        this.street2line = street2line;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
