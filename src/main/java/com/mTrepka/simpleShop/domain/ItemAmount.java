package com.mTrepka.simpleShop.domain;


import javax.persistence.*;

@Entity
public class ItemAmount {
    @Column(name = "item_amount")
    @Id
    @GeneratedValue
    private long id;
    private int amount;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Cart Cart;


    public com.mTrepka.simpleShop.domain.Cart getCart() {
        return Cart;
    }

    public void setCart(com.mTrepka.simpleShop.domain.Cart cart) {
        Cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
