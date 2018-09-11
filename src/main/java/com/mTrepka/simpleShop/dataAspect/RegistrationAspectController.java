package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.Order;
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

@Aspect
public class RegistrationAspectController {
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.postRegister(..)) &&"+
            "args(email,request)")
    void userLogin(String email,HttpServletRequest request){
        UserLog log = new UserLog();
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr()+"/"+request.getLocalAddr());
        log.setDescription("Register email "+email);
        log.setType("REGISTER");
        System.out.println(log.toString());
        logService.add(log);
    }
}
