package com.mTrepka.simpleShop.dataAspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
public class UserAspectController {


    @Before("execution(*  com.mTrepka.simpleShop.controller.AppRestController.*(..))")
    void simple(){
        System.out.println("Test Aspect");
    }
}
