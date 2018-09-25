package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.shop.ItemAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAmountRepository extends JpaRepository<ItemAmount, Long> {
}
