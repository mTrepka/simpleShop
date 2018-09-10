package com.mTrepka.simpleShop.utility;

import org.springframework.web.servlet.ModelAndView;

public class CustomModel {
    public static ModelAndView getCustomModelAndView(String view){
        return new ModelAndView(view)
                .addObject("title","Simple Shop");
    }
}
