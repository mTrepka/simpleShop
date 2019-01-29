package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.domain.Adress;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public interface AppUser {
    @GetMapping("/shop/cart/buy")
    ModelAndView userBuyGet();

    @PostMapping("/shop/cart/buy")
    ModelAndView userBuyPost(int shipping, Adress address);

    @GetMapping("/settings")
    ModelAndView userSettings();

    @PostMapping("/settings")
    ModelAndView userSettingsChange(String name, String lastName, String password, String repeatPassword, String oldPassword);

    @GetMapping("/settings/history")
    ModelAndView userHistory();

    @GetMapping("/settings/security")
    ModelAndView userSecurity();

    @GetMapping("/settings/address")
    ModelAndView userGetAddress();

    @PostMapping("/settings/address")
    ModelAndView userPostAddress(Adress adress);
}
