package com.mTrepka.simpleShop.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShippingOption {
    @Column(name = "shippingOption_id")
    @Id
    @GeneratedValue
    private long id;
    private double cost;
    private String name;
}
