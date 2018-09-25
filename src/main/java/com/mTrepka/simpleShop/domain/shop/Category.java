package com.mTrepka.simpleShop.domain.shop;


import javax.persistence.*;
import java.util.List;


@Entity
public class Category {
    @Column(name = "category_id")
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany
    private List<Item> items;


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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
