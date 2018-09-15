package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.Cart;

public interface CartService {
    Cart getCurrentUserCart();
    void save(Cart c);
}
