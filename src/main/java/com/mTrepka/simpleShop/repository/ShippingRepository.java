package com.mTrepka.simpleShop.repository;


import com.mTrepka.simpleShop.domain.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Integer> {
}
