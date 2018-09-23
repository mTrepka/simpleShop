package com.mTrepka.simpleShop.service.shop;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.repository.CartRepository;
import com.mTrepka.simpleShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Override
    public Cart getCurrentUserCart() {
        return cartRepository.findByUser(userService.getCurrentUser());
    }

    @Override
    public void save(Cart c) {
        cartRepository.saveAndFlush(c);
    }
}
