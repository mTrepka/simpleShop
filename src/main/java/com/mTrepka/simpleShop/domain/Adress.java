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
}
