package com.mTrepka.simpleShop.service.shop;


import com.mTrepka.simpleShop.domain.shop.Cart;

public interface CartService {
    Cart getCurrentUserCart();
    void save(Cart c);

    void removeItemByIdFromCart(Cart cart, int intValue);
}
