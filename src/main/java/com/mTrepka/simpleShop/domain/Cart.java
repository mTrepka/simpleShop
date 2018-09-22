package com.mTrepka.simpleShop.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Cart {
    @Column(name = "cart_id")
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<ItemAmount> items;
    @OneToOne
    private User user;

    public double  getValue(){
        return items.stream().mapToDouble(e -> e.getItem().getValue()*e.getAmount()).sum();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemAmount> getItems() {
        return items;
    }

    public void setItems(List<ItemAmount> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
