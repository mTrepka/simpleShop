package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.dataAspect.RegistrationAspectController;
import com.mTrepka.simpleShop.dataAspect.UserAspectController;
import com.mTrepka.simpleShop.domain.User;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {
    @Bean
    UserAspectController userAspectController(){
        return new UserAspectController();
    }
    @Bean
    RegistrationAspectController registrationAspectController(){return new RegistrationAspectController();}
}
