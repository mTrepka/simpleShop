package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.Adress;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.service.AddressService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.service.shop.ShippingOptionService;
import com.mTrepka.simpleShop.utility.CustomModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController()
@EnableAspectJAutoProxy
@AllArgsConstructor
public class AppUserController implements AppUser {
    private final UserService userService;
    private final CustomModel customModel;
    private final ShippingOptionService shippingOptionService;
    private final AddressService addressService;

    @Override
    public ModelAndView userSettings() {
        return customModel.getCustomModelAndView("user/settings");
    }


    @Override
    public ModelAndView userSettingsChange(@Valid String name, @Valid String lastName, @Valid String password, @Valid String repeatPassword, @Valid String oldPassword) {
        return customModel.getCustomModelAndView("user/settings")
                .addObject("message", userService.changeUser(name, lastName, password, repeatPassword, oldPassword));
    }

    @Override
    public ModelAndView userHistory() {
        return customModel.getCustomModelAndView("user/history")
                .addObject("orders", userService.getCurrentUser().getOrders());
    }

    @Override
    public ModelAndView userSecurity() {
        return customModel.getCustomModelAndView("user/security")
                .addObject("logs", userService.getCurrentUser().getLogs());
    }

    @Override
    public ModelAndView userGetAddress() {
        return customModel.getCustomModelAndView("user/address")
                .addObject("address", userService.getCurrentUser().getAdress());
    }

    @Override
    public ModelAndView userPostAddress(@Valid Adress adress) {
        addressService.save(adress);
        return customModel.getCustomModelAndView("user/address")
                .addObject("address", userService.getCurrentUser().getAdress());
    }


    @Override
    public ModelAndView userBuyGet() {
        User currentUser = userService.getCurrentUser();
        return customModel.getCustomModelAndView("shop/buy")
                .addObject("items", currentUser.getCart().getItems())
                .addObject("shippingList", shippingOptionService.findAll())
                .addObject("address", currentUser.getAdress())
                .addObject("value", currentUser.getCart().getValue());
    }


    @Override
    public ModelAndView userBuyPost(@Valid int shipping, @Valid Adress address) {
        userService.buyCart(shipping, address);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item has been bought");
    }
}
