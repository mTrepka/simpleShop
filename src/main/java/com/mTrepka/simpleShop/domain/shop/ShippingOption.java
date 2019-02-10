package com.mTrepka.simpleShop.domain.shop;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ShippingOption {
    @Column(name = "shippingOption_id")
    @Id
    @GeneratedValue
    private int id;
    private double cost;
    private String name;
}
