package com.mTrepka.simpleShop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationController {
    @GetMapping("/")
    ModelAndView getIndex();
    @GetMapping("/login")
    ModelAndView getLogin();
    @GetMapping("/register")
    ModelAndView getRegister(HttpServletRequest request);
    @PostMapping("/register")
    ModelAndView postRegister(String email,HttpServletRequest request);
    @GetMapping("/register/{code}")
    ModelAndView getSecondPartRegister(String code,HttpServletRequest request);
    @PostMapping("/register/")
    ModelAndView postSecondPartRegister(String code,  String firstPass, String secondPass, HttpServletRequest request, String name, String lastName);
    @GetMapping("/logout")
    ModelAndView getLogout(HttpServletRequest request);

    @GetMapping("/shop/cart/")
    ModelAndView getCart();

    @GetMapping("/shop/cart/remove-{itemId}")
    ModelAndView getCartRemoveItem(Integer id);
    @GetMapping("/shop/item/{itemId}")
    ModelAndView getItem(String itemId);
    @PostMapping("/shop/item/{itemId}")
    ModelAndView addItemToCart(int id,int amount,HttpServletRequest request);
    @GetMapping("/shop/{category}")
    ModelAndView getByCategory(String category);

    @GetMapping("/access-denied")
    ModelAndView accessDenied();
}
