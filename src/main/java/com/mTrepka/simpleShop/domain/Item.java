package com.mTrepka.simpleShop.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Item {
    @Column(name = "item_id")
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double value;
    private String description;
    @ManyToOne
    private Category category;
    @OneToMany(targetEntity = Order.class)
    private List<Order> orders;
    @OneToOne(targetEntity = Item.class)
    private Cart cart;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", orders=" + orders +
                ", cart=" + cart +
                ", active=" + active +
                '}';
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
