package com.mTrepka.simpleShop.service.shop;


import com.mTrepka.simpleShop.domain.shop.Cart;
import com.mTrepka.simpleShop.domain.shop.ItemAmount;
import com.mTrepka.simpleShop.repository.CartRepository;
import com.mTrepka.simpleShop.repository.ItemAmountRepository;
import com.mTrepka.simpleShop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ItemAmountRepository itemAmountRepository;
    @Override
    public Cart getCurrentUserCart() {
        return cartRepository.findByUser(userService.getCurrentUser());
    }

    @Override
    public void save(Cart c) {
        cartRepository.saveAndFlush(c);
    }

    @Override
    public void removeItemByIdFromCart(Cart cart, int intValue) {
        List<ItemAmount> items = cart.getItems();
        items.remove(itemAmountRepository.getOne((long) intValue));
        itemAmountRepository.delete(itemAmountRepository.getOne((long) intValue));
        save(cart);
    }
}
