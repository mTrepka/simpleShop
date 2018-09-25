package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.OrderStatus;
import com.mTrepka.simpleShop.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> getAll() {
        return orderStatusRepository.findAll();
    }
}
