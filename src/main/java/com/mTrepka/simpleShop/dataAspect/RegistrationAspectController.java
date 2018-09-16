package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.Order;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Aspect
public class RegistrationAspectController {
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @After("execution(* com.mTrepka.simpleShop.controller.AppRestController.postRegister(..)) &&"+
            "args(email,request)")
    void userEmailRegister(String email,HttpServletRequest request){
        UserLog log = new UserLog();
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr());
        log.setSecondIp(request.getLocalAddr());
        log.setDescription("Register email "+email);
        log.setType("REGISTER");
        log.setUser(userService.findUserByEmail(email));
        System.out.println(log.toString());
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.postSecondPartRegister(..)) &&"+
            "args(code,firstPass,secondPass,request,name,lastName)")
    void userRegister(String code, String firstPass, String secondPass,HttpServletRequest request,String name,String lastName){
        User user = userService.findUserByCode(code);
        if(Objects.nonNull(user)){
        String email = user.getEmail();
        UserLog log = new UserLog();
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr());
        log.setSecondIp(request.getLocalAddr());
        log.setDescription("Register user "+email);
        log.setType("REGISTER");
        log.setUser(userService.findUserByEmail(email));
        System.out.println(log.toString());
        logService.add(log);}
    }

}
