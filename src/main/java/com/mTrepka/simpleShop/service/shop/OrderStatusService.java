package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> getAll();
}
