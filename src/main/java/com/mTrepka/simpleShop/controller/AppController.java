package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.configuration.CustomUserDetailsService;
import com.mTrepka.simpleShop.dataAspect.RegistrationAspectController;
import com.mTrepka.simpleShop.dataAspect.UserAspectController;
import com.mTrepka.simpleShop.domain.User;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class AppController {
    @Bean
    UserAspectController userAspectController(){
        return new UserAspectController();
    }
    @Bean
    RegistrationAspectController registrationAspectController(){return new RegistrationAspectController();}
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Resource(name = "customUserDetailsService")
    private CustomUserDetailsService customUserDetailsService;
}
