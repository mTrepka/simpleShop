package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.utility.CustomModel;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@EnableAspectJAutoProxy

public class AppRestController implements ApplicationController{


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
    public ModelAndView getRegister() {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    @PostMapping("/register")
    public ModelAndView postRegister(String email) {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    @GetMapping("/register/{code}")
    public ModelAndView getSecondPartRegister(@PathVariable("path") String code) {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    @PostMapping("/register/{code}")
    public ModelAndView postSecondPartRegister(String firstPass, String secondPass) {
        return CustomModel.getCustomModelAndView("index");
    }

    @Override
    @GetMapping("/logout")
    public ModelAndView getLogout() {
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
