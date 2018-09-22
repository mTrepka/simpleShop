package com.mTrepka.simpleShop.domain;


import javax.persistence.*;

@Entity
public class Adress {
    @Column(name = "adress_id")
    @Id
    @GeneratedValue
    private long id;
    private String city;
    private String country;
    private String postalAdress;
    private String homeNumber;
    private String street1line;
    @OneToOne(targetEntity = User.class)
    private User user;

    public boolean equals(Adress obj) {
        return city.equals(obj.getCity()) && country.equals(obj.getCountry()) && postalAdress.equals(obj.getPostalAdress()) && homeNumber.equals(obj.getHomeNumber()) && street1line.equals(obj.getStreet1line());
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
