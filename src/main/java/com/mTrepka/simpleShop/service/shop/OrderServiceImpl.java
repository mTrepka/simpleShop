package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.Order;
import com.mTrepka.simpleShop.domain.shop.OrderStatus;
import com.mTrepka.simpleShop.repository.OrderRepository;
import com.mTrepka.simpleShop.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Override
    public void save(Order o) {
        orderRepository.save(o);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(int id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void update(int id, int statusId) {
        Order order = orderRepository.getOne(id);
        OrderStatus orderStatus = orderStatusRepository.getOne(statusId);
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }
}
