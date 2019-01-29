package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.utility.CustomUserLog;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
public class RegistrationAspectController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @After("execution(* com.mTrepka.simpleShop.controller.AppController.postRegister(..)) &&" +
            "args(email,request)")
    void userEmailRegister(String email,HttpServletRequest request){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Register email "+email);
        log.setType("REGISTER");
        log.setUser(userService.findUserByEmail(email));
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppController.postSecondPartRegister(..)) &&" +
            "args(code,firstPass,secondPass,request,name,lastName)")
    void userRegister(String code, String firstPass, String secondPass,HttpServletRequest request,String name,String lastName){
        User user = userService.findUserByCode(code);
        if(Objects.nonNull(user)){
        String email = user.getEmail();
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Register user "+email);
        log.setType("REGISTER");
        log.setUser(userService.findUserByEmail(email));
        logService.add(log);}
    }

}
