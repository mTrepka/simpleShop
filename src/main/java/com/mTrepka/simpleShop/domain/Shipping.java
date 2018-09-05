package com.mTrepka.simpleShop.domain;

import javax.persistence.*;


@Entity
public class Shipping {
    @Column(name = "shipping_id")
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(targetEntity = ShippingOption.class)
    private ShippingOption option;
    @ManyToOne(targetEntity = Adress.class)
    private Adress adress;
    @OneToOne
    private Order order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShippingOption getOption() {
        return option;
    }

    public void setOption(ShippingOption option) {
        this.option = option;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
