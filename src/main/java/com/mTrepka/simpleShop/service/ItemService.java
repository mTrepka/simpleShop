package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.Category;
import com.mTrepka.simpleShop.domain.Item;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ItemService{
    //List<Item> findItemByCategory(Category category);
    //List<Item> findItemByCategoryName(String categoryName);
    List<Category> getBaseCategories();
    List<Item> getLastsItemsByCategory(String category, int amount);

    Item getItemById(long itemId);
    void addItemToCart(long id, int amount);
    void addItemToCart(Item item,int amount);
}
