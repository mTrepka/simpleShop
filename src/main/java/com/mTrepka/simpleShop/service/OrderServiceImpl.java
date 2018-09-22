package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.Order;
import com.mTrepka.simpleShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public void save(Order o) {
        orderRepository.save(o);
    }
}
