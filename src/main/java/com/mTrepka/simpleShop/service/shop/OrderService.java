package com.mTrepka.simpleShop.service.shop;


import com.mTrepka.simpleShop.domain.shop.Order;

import java.util.List;

public interface OrderService {
    void save(Order o);
    List<Order> getAll();

    Order getById(int id);

    void update(int id, int statusId);
}
