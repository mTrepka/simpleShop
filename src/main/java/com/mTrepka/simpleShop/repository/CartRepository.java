package com.mTrepka.simpleShop.repository;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
