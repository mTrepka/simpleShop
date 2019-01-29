package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.domain.shop.ShippingOption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

interface AppAdmin {
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
    ModelAndView editGetItem(int id);

    @PostMapping("/admin/item/edit/{id}")
    ModelAndView editPostItem(Item item, String cat, HttpServletRequest request, MultipartFile myFile);

    @GetMapping("/admin/logs")
    ModelAndView getLogs();

    @PostMapping("/admin/logs")
    ModelAndView getLogsWithFilter(String ip, String secondIp, String type);

    @PostMapping("/admin/item/delete/{id}")
    ModelAndView removeItem(int id, HttpServletRequest request);

    @GetMapping("/admin/shipping-option")
    ModelAndView getShippingOption();

    @GetMapping("/admin/shipping-option-new")
    ModelAndView getAddShippingOption();

    @PostMapping("/admin/shipping-option-new")
    ModelAndView postAddShippingOption(ShippingOption shipping, HttpServletRequest request);

    @GetMapping("/admin/shipping-option/{id}")
    ModelAndView editGetShippingOption(int id);

    @GetMapping("/admin/shipping-option-del/{id}")
    ModelAndView removeShippingOption(int id, HttpServletRequest request);

    @PostMapping("/admin/shipping-option/{}")
    ModelAndView editPostShippingOption(ShippingOption shipping, HttpServletRequest request);

    @GetMapping("/admin/orders")
    ModelAndView ordersGet(HttpServletRequest request);

    @GetMapping("/admin/order/{id}")
    ModelAndView ordersGet(int id, HttpServletRequest request);

    @GetMapping("/admin/categories")
    ModelAndView getCategories();

    @GetMapping("/admin/new-category")
    ModelAndView getNewCategory();

    @PostMapping("/admin/new-category")
    ModelAndView postNewCategory(String name, Integer parentId);

    @GetMapping("/admin/category/delete/{id}")
    ModelAndView getDeleteCategory(int categoryId);

    @GetMapping("/admin/category/edit/{id}")
    ModelAndView getEditCategory(int categoryId);

    @PostMapping("/admin/category/edit/{id}")
    ModelAndView postEditCategory(int categoryId, String name);

    @PostMapping("/admin/order/{id}")
    ModelAndView ordersUpdate(int id, int statusId, HttpServletRequest request);

}
