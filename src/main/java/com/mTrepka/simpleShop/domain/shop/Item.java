package com.mTrepka.simpleShop.domain.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
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
    private byte[] image;
    private boolean active;
}
