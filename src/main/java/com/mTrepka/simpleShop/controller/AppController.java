package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.shop.Cart;
import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.service.shop.CartService;
import com.mTrepka.simpleShop.service.shop.CategoryService;
import com.mTrepka.simpleShop.service.shop.ItemService;
import com.mTrepka.simpleShop.utility.CustomModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;


@RestController
@EnableAspectJAutoProxy
@AllArgsConstructor
public class AppController implements ApplicationController {
    private final UserService userService;
    private final ItemService itemService;
    private final CustomModel customModel;
    private final CategoryService categoryService;
    private final CartService cartService;

    @Override
    public ModelAndView getIndex() {
        return customModel.getCustomModelAndView("index");
    }

    @Override
    public ModelAndView getLogin() {
        return customModel.getCustomModelAndView("login");
    }

    @Override
    public ModelAndView getRegister(HttpServletRequest request) {
        return customModel.getCustomModelAndView("register");
    }

    @Override
    public ModelAndView postRegister(@Valid String email, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerInfo")
                .addObject("success", userService.registerEmail(email));
    }

    @Override
    public ModelAndView getSecondPartRegister(@PathVariable("code") String code, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerCode")
                .addObject("code", code);
    }

    @Override
    public ModelAndView postSecondPartRegister(String code, String firstPass, String secondPass, HttpServletRequest request, String name, String lastName) {
        String info;
        if (userService.registerUser(firstPass, secondPass, name, lastName, code))
            info = "Uzytkownik zarejestrowany, można się zalogowac.";
        else
            info = "Zły kod";
        return customModel.getCustomModelAndView("info")
                .addObject("info", info);
    }

    @Override

    public ModelAndView getLogout(HttpServletRequest request) {
        return customModel.getCustomModelAndView("index");
    }

    /**********************************************************/

    @Override
    public ModelAndView getCart() {
        Cart cart = userService.getCurrentUser().getCart();
        return customModel.getCustomModelAndView("shop/cart")
                .addObject("items", cart.getItems())
                .addObject("value", cart.getValue());
    }

    @Override
    public ModelAndView getCartRemoveItem(@PathVariable("itemId") Integer id) {
        Cart cart = userService.getCurrentUser().getCart();
        cartService.removeItemByIdFromCart(cart, id.intValue());
        return customModel.getCustomModelAndView("shop/cart")
                .addObject("items", cart.getItems())
                .addObject("value", cart.getValue());
    }

    @Override
    public ModelAndView addItemToCart(@PathVariable("itemId") int id, @Valid int amount, HttpServletRequest request) {
        if (amount != 0 && id != 0)
            itemService.addItemToCart(id, amount);
        Item item = itemService.getItemById(id);
        if (Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item", item);
    }

    @Override
    public ModelAndView getByCategory(@PathVariable("category") String category) {
        Category cat = categoryService.getByName(category);
        return customModel.getCustomModelAndView("shop/category")
                .addObject("items", itemService.getLastsItemsByCategory(category, 25))
                .addObject("category", cat);
    }

    @Override
    public ModelAndView getItem(@PathVariable("itemId") String itemId) {
        Item item = itemService.getItemById(Integer.parseInt(itemId));
        if (Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item", item);
    }

    @Override
    public ModelAndView accessDenied() {
        return customModel.getCustomModelAndView("accessDenied");
    }
}