package com.mTrepka.simpleShop.controller;


import org.springframework.web.servlet.ModelAndView;

public interface ApplicationController {
    ModelAndView getIndex();
    ModelAndView getLogin();
    ModelAndView getRegister();
    ModelAndView postRegister(String email);
    ModelAndView getSecondPartRegister(String code);
    ModelAndView postSecondPartRegister(String firstPass, String secondPass);
    ModelAndView getLogout();

    ModelAndView getCart();
    ModelAndView getItem(String id);
    ModelAndView getByCategory(String category);
    ModelAndView addItemToCart(String id);
}
