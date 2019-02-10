package com.mTrepka.simpleShop.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
