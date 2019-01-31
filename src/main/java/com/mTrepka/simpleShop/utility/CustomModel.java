package com.mTrepka.simpleShop.utility;

import com.mTrepka.simpleShop.service.shop.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor()
public class CustomModel {
    @Value("${app.name}")
    private String name;
    private final CategoryService categoryService;

    public ModelAndView getCustomModelAndView(String view) {
        return new ModelAndView(view)
                .addObject("title", name)
                .addObject("href", "http://localhost:8080/")
                .addObject("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString())
                .addObject("categories", categoryService.getRootCategory().getChildCategory());
    }
}
