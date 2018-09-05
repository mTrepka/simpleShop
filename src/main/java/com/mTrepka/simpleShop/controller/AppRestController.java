package com.mTrepka.simpleShop.controller;


import com.mTrepka.simpleShop.domain.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy

public class AppRestController {
    @GetMapping("/")
    String hello(){
        User user = new User();
        return "Hello World";
    }

}
