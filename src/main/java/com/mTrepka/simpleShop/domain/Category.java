package com.mTrepka.simpleShop.domain;


import javax.persistence.*;
import java.util.List;


@Entity
public class Category {
    @Column(name = "category_id")
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(targetEntity = Item.class)
    private List<Item> items;
}
