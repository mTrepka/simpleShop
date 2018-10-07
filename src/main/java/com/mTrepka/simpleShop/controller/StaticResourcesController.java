package com.mTrepka.simpleShop.controller;


import com.lowagie.text.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping("/static")
public class StaticResourcesController {
    @GetMapping("/favicon.ico")
    public Object favicon(){
        URL imageUrl = getClass().getResource("/static/favicon.ico");
        try {
            return Image.getInstance(imageUrl).getOriginalData();
        }catch (Exception e){

        }
        return null;
    }
}
