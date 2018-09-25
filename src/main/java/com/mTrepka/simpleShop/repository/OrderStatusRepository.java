package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.shop.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
}
