package com.mTrepka.simpleShop.domain;

import javax.persistence.*;


@Entity
public class Cart {
    @Column(name = "cart_id")
    @Id
    @GeneratedValue
    private long id;

}
