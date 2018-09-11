package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.Order;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.service.UserService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Aspect
public class UserAspectController {
    @Autowired
    UserService userService;

    void userLogin(HttpServletRequest request){
        UserLog log = new UserLog();
        log.setUser(userService.getCurrentUser());
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr()+"/"+request.getLocalAddr());
        log.setDescription("User log in");
        log.setType("LOGIN");
    }
    void userLogout(HttpServletRequest request){
        UserLog log = new UserLog();
        log.setUser(userService.getCurrentUser());
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr()+"/"+request.getLocalAddr());
        log.setDescription("User log out");
        log.setType("LOGOUT");
    }
    void userShopping(HttpServletRequest request, Order order){
        UserLog log = new UserLog();
        log.setUser(userService.getCurrentUser());
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr()+"/"+request.getLocalAddr());
        log.setDescription("User buy order"+order.getId());
        log.setType("SHOPPING");
    }
}
