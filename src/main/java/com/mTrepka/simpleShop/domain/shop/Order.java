package com.mTrepka.simpleShop.domain.shop;


import com.mTrepka.simpleShop.domain.User;

import javax.persistence.*;

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
    private OrderStatus status;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Cart getCart() {
        return cart;
    }
}
