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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

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
