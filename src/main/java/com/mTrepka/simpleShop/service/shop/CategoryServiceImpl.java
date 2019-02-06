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
            Category par = parent.get();
            newCat.setParent(par);
        }
        categoryRepository.save(newCat);

    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById((long)categoryId);
    }

    @Override
    public Category editAndSave(int id, String name, int parentId) {
        Category c = categoryRepository.findById((long)id).get();
        c.setName(name);
        c.setParent(categoryRepository.getOne((long) parentId));
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category getById(int categoryId) {
        return categoryRepository.getOne((long)categoryId);
    }

    @Override
    public Category getRootCategory() {
        return categoryRepository.findByName("root");
    }

    @Override
    public Category getByName(String category) {
        return categoryRepository.findByName(category);
    }
}
