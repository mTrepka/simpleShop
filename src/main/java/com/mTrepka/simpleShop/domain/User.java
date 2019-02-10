package com.mTrepka.simpleShop.domain;


import com.mTrepka.simpleShop.domain.shop.Cart;
import com.mTrepka.simpleShop.domain.shop.Order;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String email;
    private String code;
    private String password;
    private String name;
    private String lastName;
    @OneToOne
    private Cart cart;
    @OneToOne(targetEntity = Adress.class)
    private Adress adress;
    private boolean active = false;
    @CreationTimestamp
    private Timestamp createDate;
    @UpdateTimestamp
    private Timestamp updateTimestamp;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany
    private List<UserLog> logs;


    @OneToMany(targetEntity = Order.class)
    private List<Order> orders;


}
