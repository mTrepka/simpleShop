package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    void newCategory(String name, int parentId);

    void deleteCategory(int categoryId);

    Category editAndSave(int id, String name, int parentId);

    Category getById(int categoryId);

    Category getRootCategory();
}
