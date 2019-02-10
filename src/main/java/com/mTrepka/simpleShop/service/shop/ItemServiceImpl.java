package com.mTrepka.simpleShop.service.shop;


import com.mTrepka.simpleShop.domain.shop.Cart;
import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.domain.shop.ItemAmount;
import com.mTrepka.simpleShop.repository.CategoryRepository;
import com.mTrepka.simpleShop.repository.ItemAmountRepository;
import com.mTrepka.simpleShop.repository.ItemRepository;
import com.mTrepka.simpleShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ItemAmountRepository itemAmountRepository;

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
        ItemAmount itemAmount = new ItemAmount();
        itemAmount.setAmount(amount);
        itemAmount.setItem(itemRepository.getById(id));
        itemAmount.setCart(cartService.getCurrentUserCart());
        itemAmountRepository.saveAndFlush(itemAmount);
    }

    @Override
    public void addItemToCart(Item item, int amount) {
        Cart cart = cartService.getCurrentUserCart();
        ItemAmount itemAmount = new ItemAmount();
        itemAmount.setCart(cart);
        itemAmount.setAmount(amount);
        itemAmount.setItem(item);
        cart.getItems().add(itemAmount);
        itemAmountRepository.save(itemAmount);
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

    @Override
    public void saveItem(Item item, String cat, MultipartFile file) {

        try{
        item.setImage(file.getBytes());}
        catch (Exception e){ }
        item.setCategory(categoryRepository.findById(Long.parseLong(cat)).get());
        itemRepository.save(item);
    }
}
