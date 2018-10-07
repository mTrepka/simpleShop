package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.Adress;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.domain.shop.ShippingOption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    @GetMapping("/shop/item/{itemId}")
    ModelAndView getItem(String itemId);
    @PostMapping("/shop/item/{itemId}")
    ModelAndView addItemToCart(int id,int amount,HttpServletRequest request);
    @GetMapping("/shop/{category}")
    ModelAndView getByCategory(String category);

    @GetMapping("/admin/users")
    ModelAndView getUsers();
    @GetMapping("/admin/users/{id}")
    ModelAndView getUser(int id);
    @GetMapping("/admin/items")
    ModelAndView getItems();
    @GetMapping("/admin/items/{id}")
    ModelAndView getItem(int id);
    @GetMapping("/admin/new-item")
    ModelAndView getAddNewItem();
    @PostMapping("/admin/new-item")
    ModelAndView postAddNewItem(Item item, String cat, HttpServletRequest request, MultipartFile myFile);
    @GetMapping("/admin/item/edit/{id}")
    ModelAndView editGetItem(@PathVariable("id") int id);
    @PostMapping("/admin/item/edit/{id}")
    ModelAndView editPostItem(@Valid Item item, @Valid String cat, HttpServletRequest request, MultipartFile myFile);
    @GetMapping("/admin/logs")
    ModelAndView getLogs();
    @PostMapping("/admin/logs")
    ModelAndView getLogsWithFilter(String ip,String secondIp,String type);
    @PostMapping("/admin/item/delete/{id}")
    ModelAndView removeItem(@PathVariable("id") int id, HttpServletRequest request);
    @GetMapping("/admin/shipping-option")
    ModelAndView getShippingOption();
    @GetMapping("/admin/shipping-option-new")
    ModelAndView getAddShippingOption();
    @PostMapping("/admin/shipping-option-new")
    ModelAndView postAddShippingOption(@Valid ShippingOption shipping, HttpServletRequest request);
    @GetMapping("/admin/shipping-option/{id}")
    ModelAndView editGetShippingOption(@PathVariable("id") int id);
    @GetMapping("/admin/shipping-option-del/{id}")
    ModelAndView removeShippingOption(@PathVariable("id") int id, HttpServletRequest request);
    @PostMapping("/admin/shipping-option/{}")
    ModelAndView editPostShippingOption(ShippingOption shipping, HttpServletRequest request);

    @GetMapping("/shop/cart/buy")
    ModelAndView userBuyGet();

    @PostMapping("/shop/cart/buy")
    ModelAndView userBuyPost(@Valid int shipping, @Valid Adress address);

    @GetMapping("/admin/orders")
    ModelAndView ordersGet(HttpServletRequest request);
    @GetMapping("/admin/order/{id}")
    ModelAndView ordersGet(@PathVariable("id") int id, HttpServletRequest request);

    @GetMapping("/settings")
    ModelAndView userSettings();
    @PostMapping("/settings")
    ModelAndView userSettingsChange(@Valid String name, @Valid String lastName, @Valid String password, @Valid String repeatPassword, @Valid String oldPassword);
    @GetMapping("/settings/history")
    ModelAndView userHistory();
    @GetMapping("/settings/security")
    ModelAndView userSecurity();
    @GetMapping("/settings/address")
    ModelAndView userGetAddress();
    @PostMapping("/settings/address")
    ModelAndView userPostAddress(@Valid Adress adress);


    @PostMapping("/admin/order/{id}")
    ModelAndView ordersUpdate(@PathVariable("id") int id, @Valid int statusId, HttpServletRequest request);

    @GetMapping("/access-denied")
    ModelAndView accessDenied();
}
