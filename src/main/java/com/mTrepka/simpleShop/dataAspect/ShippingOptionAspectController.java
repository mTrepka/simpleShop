package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.ShippingOption;
import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.service.shop.ShippingOptionService;
import com.mTrepka.simpleShop.utility.CustomUserLog;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class ShippingOptionAspectController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private ShippingOptionService shippingOptionService;
    @After("execution(* com.mTrepka.simpleShop.controller.AppRestController.postAddShippingOption(..)) &&"+
            "args(shipping,request)")
    void addLog(ShippingOption shipping, HttpServletRequest request){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Add shipping option "+shipping.getName()+", with id "+shipping.getId());
        log.setType("ADD");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.editPostShippingOption(..)) &&"+
            "args(shipping,request)")
    void editLog(ShippingOption shipping,HttpServletRequest request){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        ShippingOption oldShippingOption = shippingOptionService.findById(shipping.getId());
        StringBuilder sb = new StringBuilder();

        if(!oldShippingOption.getName().equals(shipping.getName()))
            sb.append(" from "+oldShippingOption.getName()+", to "+shipping.getName());
        if(oldShippingOption.getCost()!=shipping.getCost())
            sb.append(" from "+oldShippingOption.getCost()+", to "+shipping.getCost());

        log.setDescription("Edit shipping option"+sb.toString()+" ,with id "+shipping.getId());
        log.setType("EDIT");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.removeShippingOption(..)) &&"+
            "args(id,request)")
    void removeLog(int id,HttpServletRequest request){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Remove shipping option with id "+id);
        log.setType("REMOVE");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }
}
