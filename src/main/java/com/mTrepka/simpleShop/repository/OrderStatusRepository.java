package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
}
