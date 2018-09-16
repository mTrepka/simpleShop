package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.domain.Item;
import com.mTrepka.simpleShop.service.ItemService;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.utility.CustomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@EnableAspectJAutoProxy

public class AppRestController implements ApplicationController{
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LogService logService;

    @Override
    @GetMapping("/")
    public ModelAndView getIndex() {
        return CustomModel.getCustomModelAndView("index").addObject("categories",itemService.getBaseCategories());
    }

    @Override
    @GetMapping("/login")
    public ModelAndView getLogin() {
        return CustomModel.getCustomModelAndView("login");
    }

    @Override
    @GetMapping("/register")
    public ModelAndView getRegister(HttpServletRequest request) {
        return CustomModel.getCustomModelAndView("register");
    }

    @Override
    @PostMapping("/register")
    public ModelAndView postRegister(@Valid String email,HttpServletRequest request) {
        return CustomModel.getCustomModelAndView("registerInfo")
                .addObject("success",userService.registerEmail(email));
    }

    @Override
    @GetMapping("/register/{code}")
    public ModelAndView getSecondPartRegister(@PathVariable("code") String code,HttpServletRequest request) {
        return CustomModel.getCustomModelAndView("registerCode")
                .addObject("code",code);
    }

    @Override
    @PostMapping("/register/")
    public ModelAndView postSecondPartRegister(String code, String firstPass, String secondPass,HttpServletRequest request,String name,String lastName) {
        String info;
        if(userService.registerUser(firstPass, secondPass, name, lastName, code))
           info = "Uzytkownik zarejestrowany, można się zalogowac.";
        else
            info = "Zły kod";
        return CustomModel.getCustomModelAndView("info")
                .addObject("info",info);
    }

    @Override
    @GetMapping("/logout")
    public ModelAndView getLogout(HttpServletRequest request) {
        return CustomModel.getCustomModelAndView("index");
    }
    /**********************************************************/

    @Override
    @GetMapping("/shop/cart/")
    public ModelAndView getCart() {
        ModelAndView customModelAndView = CustomModel.getCustomModelAndView("shop/cart");
        Cart cart = userService.getCurrentUser().getCart();
        customModelAndView.addObject("items",cart.getItems());
        customModelAndView.addObject("value",cart.getValue());
        return customModelAndView;
        //return CustomModel.getCustomModelAndView("shop/cart").addObject("items",userService.getCurrentUser().getCart().getItems()); //front do calculate himself
    }

    @Override
    @PostMapping("/shop/cart/")
    public ModelAndView addItemToCart(@Valid int id, @Valid int amount, HttpServletRequest request) {
        itemService.addItemToCart(id,amount);
        return CustomModel.getCustomModelAndView("shop/cart")
                        .addObject("items",userService.getCurrentUser().getCart().getItems());
    }

    @Override
    @GetMapping("/shop/{category}")
    public ModelAndView getByCategory(@PathVariable("category") String category) {
        return CustomModel.getCustomModelAndView("shop/category")
                .addObject("items",itemService.getLastsItemsByCategory(category,25));
    }

    @Override
    @GetMapping("/shop/item/{itemId}")
    public ModelAndView getItem(@PathVariable("itemId")String itemId) {
        Item item = itemService.getItemById(Integer.parseInt(itemId));
        if(Objects.isNull(item))
            return CustomModel.getCustomModelAndView("notFound");
        return CustomModel.getCustomModelAndView("shop/item")
                .addObject("item",item);
    }

    @GetMapping("/admin/logs")
    @Override
    public ModelAndView getLogs() {
        return CustomModel.getCustomModelAndView("admin/logs")
                .addObject("logs",logService.getAllLogs());
    }

    @PostMapping("/admin/logs")
    @Override
    public ModelAndView getLogsWithFilter(@Valid String ip,@Valid String secondIp,@Valid String type) {
        return CustomModel.getCustomModelAndView("admin/logs")
                .addObject(logService.getLogsWithFilters(ip,secondIp,type));
    }

    @Override
    @GetMapping("/admin/users")
    public ModelAndView getUsers() {
        return CustomModel.getCustomModelAndView("admin/users")
                .addObject("users",userService.getAllUsers());
    }

    @GetMapping("/admin/users/{id}")
    @Override
    public ModelAndView getUser(@PathVariable("id") int id) {
        return CustomModel.getCustomModelAndView("admin/user")
                .addObject("user",userService.findById(id));
    }

    @GetMapping("/admin/items")
    @Override
    public ModelAndView getItems() {
        return CustomModel.getCustomModelAndView("admin/items")
                .addObject("items",itemService.getAllItems());
    }

    @GetMapping("/admin/items/{id}")
    @Override
    public ModelAndView getItem(@PathVariable("id") int id) {
        return CustomModel.getCustomModelAndView("admin/item")
                .addObject("item",itemService.getItemById(id));
    }

    @GetMapping("/admin/new-item")
    @Override
    public ModelAndView getAddNewItem() {
        return CustomModel.getCustomModelAndView("admin/newItem");
    }

    @PostMapping("/admin/new-item")
    @Override
    public ModelAndView postAddNewItem(@Valid Item item) {
        itemService.addItem(item);
        return CustomModel.getCustomModelAndView("info")
                .addObject("info","Item zostal dodany!");
    }

    @GetMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editGetItem(@PathVariable("id") int id) {
        return CustomModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item",itemService.getItemById(id));
    }

    @PostMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editPostItem(Item item) {
        return CustomModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item",item)
                .addObject("info","Item został zmieniony");
    }

    @PostMapping("/admin/item/delete/{id}")
    @Override
    public ModelAndView deleteItem(@PathVariable("id") int id) {
        itemService.deleteItemById(id);
        return CustomModel.getCustomModelAndView("info")
                .addObject("info","Item zostal usunięty!");
    }
}
