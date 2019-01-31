package com.mTrepka.simpleShop.domain.shop;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Category {
    @Column(name = "category_id")
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<Item> items;
    @OneToMany(mappedBy = "parent")
    private List<Category> childCategory;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;
}
