package com.mTrepka.simpleShop.repository;


import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.domain.shop.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
