package com.mTrepka.simpleShop.utility;

import com.mTrepka.simpleShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CustomModel {
    @Autowired
    private ItemService itemService;

    public ModelAndView getCustomModelAndView(String view) {
        return new ModelAndView(view)
                .addObject("title", "Simple Shop")
                .addObject("href", "http://localhost:8080/")
                .addObject("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString())
                .addObject("categories", itemService.getAllCategory());
    }
}
