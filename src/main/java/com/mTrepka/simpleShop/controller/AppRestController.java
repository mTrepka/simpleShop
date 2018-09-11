package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.User;
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

@RestController
@EnableAspectJAutoProxy

public class AppRestController implements ApplicationController{
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/")
    public ModelAndView getIndex() {
        return CustomModel.getCustomModelAndView("index");
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
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    @PostMapping("/register/{code}")
    public ModelAndView postSecondPartRegister(@PathVariable("code") String code,String firstPass, String secondPass,HttpServletRequest request,String name,String lastName) {
        return CustomModel.getCustomModelAndView("info")
                .addObject("info","Użytkownik został zarejestrowany.");
    }

    @Override
    @GetMapping("/logout")
    public ModelAndView getLogout(HttpServletRequest request) {
        return CustomModel.getCustomModelAndView("index");
    }
    /**********************************************************/

    @Override
    public ModelAndView getCart() {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    public ModelAndView getItem(String id) {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    public ModelAndView getByCategory(String category) {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    public ModelAndView addItemToCart(String id) {
        return CustomModel.getCustomModelAndView("index");
    }
}
