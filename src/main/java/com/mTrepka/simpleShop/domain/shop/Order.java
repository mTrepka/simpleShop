package com.mTrepka.simpleShop.domain.shop;


import com.mTrepka.simpleShop.domain.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ord")//app crashes with default entity name
public class Order {
    @Column(name = "order_id")
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Shipping shipping;
    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
