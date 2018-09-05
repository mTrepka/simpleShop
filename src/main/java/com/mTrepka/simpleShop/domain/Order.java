package com.mTrepka.simpleShop.domain;


import javax.persistence.*;

@Entity(name = "ord")//app crashes with default entity name
public class Order {
    @Column(name = "order_id")
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Shipping shipping;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
