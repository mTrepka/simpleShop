package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void newCategory(String name, int parentId) {
        Optional<Category> parent = categoryRepository.findById((long)parentId);
        Category newCat = new Category();
        newCat.setName(name);
        if (parent.isPresent()){
        }
        categoryRepository.save(newCat);
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById((long)categoryId);
    }

    @Override
    public Category editAndSave(int id, String name) {
        Category c = categoryRepository.findById((long)id).get();
        c.setName(name);
        return categoryRepository.save(c);
    }

    @Override
    public Category getById(int categoryId) {
        System.out.println(categoryId);
        return categoryRepository.getOne((long)categoryId);
    }
}
