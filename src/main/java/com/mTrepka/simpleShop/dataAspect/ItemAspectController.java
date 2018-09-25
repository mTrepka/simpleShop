package com.mTrepka.simpleShop.dataAspect;


import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.utility.CustomUserLog;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class ItemAspectController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @After("execution(* com.mTrepka.simpleShop.controller.AppRestController.postAddNewItem(..)) &&"+
            "args(item,cat,request,myFile)")
    void addLog(Item item, String cat, HttpServletRequest request, MultipartFile myFile){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Add item option with id "+item.getId());
        log.setType("ADD");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.editPostItem(..)) &&"+
            "args(item,cat,request,myFile)")
    void editLog(Item item,String cat,HttpServletRequest request,MultipartFile myFile){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Edit item with id "+item.getId());
        log.setType("EDIT");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }

    @Before("execution(* com.mTrepka.simpleShop.controller.AppRestController.removeItem(..)) &&"+
            "args(id,request)")
    void removeLog(int id,HttpServletRequest request){
        UserLog log = CustomUserLog.getCustomUserLog(request);
        log.setDescription("Remove item with id "+id);
        log.setType("REMOVE");
        log.setUser(userService.getCurrentUser());
        logService.add(log);
    }
}
