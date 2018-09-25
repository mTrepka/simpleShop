package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.domain.shop.Item;
import org.springframework.web.multipart.MultipartFile;

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

    void saveItem(Item item, String cat, MultipartFile file);
}
