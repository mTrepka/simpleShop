package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.Item;
import com.mTrepka.simpleShop.domain.ShippingOption;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationController {
    ModelAndView getIndex();
    ModelAndView getLogin();
    ModelAndView getRegister(HttpServletRequest request);
    ModelAndView postRegister(String email,HttpServletRequest request);
    ModelAndView getSecondPartRegister(String code,HttpServletRequest request);
    ModelAndView postSecondPartRegister(String code,  String firstPass, String secondPass, HttpServletRequest request, String name, String lastName);
    ModelAndView getLogout(HttpServletRequest request);

    ModelAndView getCart();
    ModelAndView getItem(String itemId);
    ModelAndView addItemToCart(int id,int amount,HttpServletRequest request);
    ModelAndView getByCategory(String category);

    ModelAndView getUsers();
    ModelAndView getUser(int id);
    ModelAndView getItems();
    ModelAndView getItem(int id);
    ModelAndView getAddNewItem();

    ModelAndView postAddNewItem(Item item, String cat,HttpServletRequest request);
    ModelAndView editGetItem(int id);
    ModelAndView editPostItem(Item item,HttpServletRequest request);

    ModelAndView getShippingOption();

    ModelAndView removeItem(int id,HttpServletRequest request);
    ModelAndView getLogs();
    ModelAndView getLogsWithFilter(String ip,String secondIp,String type);


    ModelAndView userHistory();

    ModelAndView getAddShippingOption();

    ModelAndView postAddShippingOption(ShippingOption shipping,HttpServletRequest request);
    ModelAndView editGetShippingOption(int id);
    ModelAndView removeShippingOption( int id,HttpServletRequest request);
    ModelAndView editPostShippingOption(ShippingOption shipping,HttpServletRequest request);

    ModelAndView userSettings();
    ModelAndView userSettingsChange(String name, String lastName, String password, String repeatPassword, String oldPassword);
    ModelAndView userSecurity();
}
