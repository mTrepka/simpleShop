package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}