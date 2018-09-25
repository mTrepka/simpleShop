package com.mTrepka.simpleShop.repository;


import com.mTrepka.simpleShop.domain.shop.ShippingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingOptionRepository extends JpaRepository<ShippingOption, Integer> {
}
