package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
