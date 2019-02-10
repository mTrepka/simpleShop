package com.mTrepka.simpleShop.domain.shop;

import com.mTrepka.simpleShop.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Cart {
    @Column(name = "cart_id")
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(mappedBy = "cart")
    private List<ItemAmount> items;
    @OneToOne
    private User user;

    public double  getValue(){
        return items.stream().mapToDouble(e -> e.getItem().getValue()*e.getAmount()).sum();
    }
}
