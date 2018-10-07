package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.service.shop.ItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ImageResourceController {
    private ItemService itemService;

    @GetMapping("/image/{id}")
    public Object image(@PathVariable("id") int id){
        byte[] image = itemService.getItemById(id).getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    public ImageResourceController(ItemService itemService) {
        this.itemService = itemService;
    }
}
