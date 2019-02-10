package com.mTrepka.simpleShop.domain.shop;

import com.mTrepka.simpleShop.domain.Adress;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Shipping {
    @Column(name = "shipping_id")
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = ShippingOption.class)
    private ShippingOption option;
    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Adress adress;
    @OneToOne
    private Order order;
}
