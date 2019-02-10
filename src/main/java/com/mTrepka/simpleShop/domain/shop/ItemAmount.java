package com.mTrepka.simpleShop.domain.shop;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ItemAmount {
    @Column(name = "item_amount")
    @Id
    @GeneratedValue
    private long id;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
