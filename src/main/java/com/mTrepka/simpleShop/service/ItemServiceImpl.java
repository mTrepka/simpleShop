package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.domain.Category;
import com.mTrepka.simpleShop.domain.Item;
import com.mTrepka.simpleShop.repository.CategoryRepository;
import com.mTrepka.simpleShop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Override
    public List<Category> getBaseCategories() {
        List<String> categoryNames = Arrays.asList("Ubrania","Elektronika","Dom","Dziecko","Zdrowie","Motoryzacja");
        Collections.sort(categoryNames);
        return categoryNames.stream().map(e -> categoryRepository.findByName(e)).collect(Collectors.toList());
    }

    @Override
    public List<Item> getLastsItemsByCategory(String categoryName, int amount) {
        List<Item> list = itemRepository.findByCategory(categoryRepository.findByName(categoryName));
        if(amount>list.size())
        return list;
        return list.subList(0,amount);
    }

    @Override
    public Item getItemById(long itemId) {
            return itemRepository.getById(itemId);
    }

    @Override
    public void addItemToCart(long id, int amount) {
        Cart cart = cartService.getCurrentUserCart();
        cart.getItems().add(itemRepository.getOne(id));
        cartService.save(cart);
    }

    @Override
    public void addItemToCart(Item item, int amount) {
        Cart cart = cartService.getCurrentUserCart();
        cart.getItems().add(item);
        cartService.save(cart);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}