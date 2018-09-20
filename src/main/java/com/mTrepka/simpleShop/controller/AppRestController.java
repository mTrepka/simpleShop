package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.domain.Category;
import com.mTrepka.simpleShop.domain.Item;
import com.mTrepka.simpleShop.domain.ShippingOption;
import com.mTrepka.simpleShop.service.ItemService;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.ShippingOptionService;
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
    @Autowired
    private CustomModel customModel;
    @Autowired
    private ShippingOptionService shippingOptionService;

    @Override
    @GetMapping("/")
    public ModelAndView getIndex() {
        return customModel.getCustomModelAndView("index");
    }

    @Override
    @GetMapping("/login")
    public ModelAndView getLogin() {
        return customModel.getCustomModelAndView("login");
    }

    @Override
    @GetMapping("/register")
    public ModelAndView getRegister(HttpServletRequest request) {
        return customModel.getCustomModelAndView("register");
    }

    @Override
    @PostMapping("/register")
    public ModelAndView postRegister(@Valid String email, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerInfo")
                .addObject("success",userService.registerEmail(email));
    }

    @Override
    @GetMapping("/register/{code}")
    public ModelAndView getSecondPartRegister(@PathVariable("code") String code, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerCode")
                .addObject("code",code);
    }

    @Override
    @PostMapping("/register/")
    public ModelAndView postSecondPartRegister(String code, String firstPass, String secondPass, HttpServletRequest request, String name, String lastName) {
        String info;
        if(userService.registerUser(firstPass, secondPass, name, lastName, code))
            info = "Uzytkownik zarejestrowany, można się zalogowac.";
        else
            info = "Zły kod";
        return customModel.getCustomModelAndView("info")
                .addObject("info",info);
    }

    @Override
    @GetMapping("/logout")
    public ModelAndView getLogout(HttpServletRequest request) {
        return customModel.getCustomModelAndView("index");
    }

    /**********************************************************/

    @Override
    @GetMapping("/shop/cart/")
    public ModelAndView getCart() {
        Cart cart = userService.getCurrentUser().getCart();
        return customModel.getCustomModelAndView("shop/cart")
                .addObject("items", cart.getItems())
                .addObject("value", cart.getValue());
        //return CustomModel.getCustomModelAndView("shop/cart").addObject("items",userService.getCurrentUser().getCart().getItems()); //front do calculate himself
    }

    @Override
    @PostMapping("/shop/item/{itemId}")
    public ModelAndView addItemToCart(@PathVariable("itemId") int id, @Valid int amount, HttpServletRequest request) {
        if (amount != 0 && id != 0)
        itemService.addItemToCart(id,amount);
        Item item = itemService.getItemById(id);
        if (Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item", item);
    }

    @Override
    @GetMapping("/shop/{category}")
    public ModelAndView getByCategory(@PathVariable("category") String category) {
        return customModel.getCustomModelAndView("shop/category")
                .addObject("items",itemService.getLastsItemsByCategory(category,25));
    }

    @Override
    @GetMapping("/shop/item/{itemId}")
    public ModelAndView getItem(@PathVariable("itemId")String itemId) {
        Item item = itemService.getItemById(Integer.parseInt(itemId));
        if(Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item",item);
    }

    @GetMapping("/admin/logs")
    @Override
    public ModelAndView getLogs() {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs",logService.getAllLogs());
    }

    @PostMapping("/admin/logs")
    @Override
    public ModelAndView getLogsWithFilter(@Valid String ip, @Valid String secondIp, @Valid String type) {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs", logService.getLogsWithFilters(ip, secondIp, type));
    }

    @Override
    @GetMapping("/admin/users")
    public ModelAndView getUsers() {
        return customModel.getCustomModelAndView("admin/users")
                .addObject("users",userService.getAllUsers());
    }

    @GetMapping("/admin/users/{id}")
    @Override
    public ModelAndView getUser(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/user")
                .addObject("user",userService.findById(id));
    }

    @GetMapping("/admin/items")
    @Override
    public ModelAndView getItems() {
        return customModel.getCustomModelAndView("admin/items")
                .addObject("items",itemService.getAllItems());
    }

    @GetMapping("/admin/items/{id}")
    @Override
    public ModelAndView getItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/item")
                .addObject("item",itemService.getItemById(id));
    }

    @GetMapping("/admin/new-item")
    @Override
    public ModelAndView getAddNewItem() {
        return customModel.getCustomModelAndView("admin/newItem").addObject("categoryList", itemService.getAllCategory())
                .addObject("item", new Item())
                .addObject("category", new Category());
    }

    @PostMapping("/admin/new-item")
    @Override
    public ModelAndView postAddNewItem(@Valid Item item, @Valid String cat,HttpServletRequest request) {
        itemService.saveItem(item, cat);
        return customModel.getCustomModelAndView("info")
                .addObject("info","Item zostal dodany!");
    }

    @GetMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editGetItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("newItem", itemService.getItemById(id));
    }

    @PostMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editPostItem(Item item,HttpServletRequest request) {
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item",item)
                .addObject("info","Item został zmieniony");
    }

    @PostMapping("/admin/item/delete/{id}")
    @Override
    public ModelAndView removeItem(@PathVariable("id") int id,HttpServletRequest request) {
        itemService.deleteItemById(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item zostal usunięty!");
    }

    @GetMapping("/admin/shipping-option")
    @Override
    public ModelAndView getShippingOption() {
        return customModel.getCustomModelAndView("admin/shippingOptions")
                .addObject("shippingList", shippingOptionService.findAll());
    }

    @GetMapping("/admin/shipping-option-new")
    @Override
    public ModelAndView getAddShippingOption() {
        return customModel.getCustomModelAndView("admin/newShippingOption")
                .addObject("shipping", new ShippingOption());
    }

    @PostMapping("/admin/shipping-option-new")
    @Override
    public ModelAndView postAddShippingOption(@Valid ShippingOption shipping,HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been created.");
    }

    @GetMapping("/admin/shipping-option/{id}")
    @Override
    public ModelAndView editGetShippingOption(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/shippingOptionEdit")
                .addObject("shipping", shippingOptionService.findById(id));
    }

    @GetMapping("/admin/shipping-option-del/{id}")
    @Override
    public ModelAndView removeShippingOption(@PathVariable("id") int id,HttpServletRequest request) {
        shippingOptionService.delete(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been deleted.");
    }

    @PostMapping("/admin/shipping-option/{}")
    @Override
    public ModelAndView editPostShippingOption(ShippingOption shipping,HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been changed.");
    }


    @GetMapping("/settings")
    @Override
    public ModelAndView userSettings() {
        return customModel.getCustomModelAndView("user/settings");
    }

    @PostMapping("/settings")
    @Override
    public ModelAndView userSettingsChange(@Valid String name, @Valid String lastName, @Valid String password, @Valid String repeatPassword, @Valid String oldPassword) {
        return customModel.getCustomModelAndView("user/settings")
                .addObject("message", userService.changeUser(name, lastName, password, repeatPassword, oldPassword));
    }

    @GetMapping("/settings/history")
    @Override
    public ModelAndView userHistory() {
        return customModel.getCustomModelAndView("user/history")
                .addObject(userService.getCurrentUser().getOrders());
    }

    @GetMapping("/settings/security")
    @Override
    public ModelAndView userSecurity() {
        return customModel.getCustomModelAndView("user/security")
                .addObject("logs", userService.getCurrentUser().getLogs());
    }


}