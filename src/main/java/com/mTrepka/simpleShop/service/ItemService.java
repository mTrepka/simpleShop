package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.Category;
import com.mTrepka.simpleShop.domain.Item;

import java.util.List;

public interface ItemService{
    List<Category> getBaseCategories();
    List<Item> getLastsItemsByCategory(String category, int amount);
    Item getItemById(long itemId);
    void addItemToCart(long id, int amount);
    void addItemToCart(Item item,int amount);
    List<Item> getAllItems();

    void addItem(Item item);

    List<Category> getAllCategory();

    void deleteItemById(long id);

    void saveItem(Item item, String cat);
}
