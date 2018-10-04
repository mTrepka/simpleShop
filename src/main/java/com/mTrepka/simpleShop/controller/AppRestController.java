package com.mTrepka.simpleShop.controller;


import com.lowagie.text.Image;
import com.mTrepka.simpleShop.domain.Adress;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.domain.shop.Cart;
import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.domain.shop.ShippingOption;
import com.mTrepka.simpleShop.service.AddressService;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.service.shop.ItemService;
import com.mTrepka.simpleShop.service.shop.OrderService;
import com.mTrepka.simpleShop.service.shop.OrderStatusService;
import com.mTrepka.simpleShop.service.shop.ShippingOptionService;
import com.mTrepka.simpleShop.utility.CustomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URL;
import java.util.Objects;


@RestController
@EnableAspectJAutoProxy
public class AppRestController implements ApplicationController{
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LogService logService;
    @Autowired
    private CustomModel customModel;
    @Autowired
    private ShippingOptionService shippingOptionService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Override
    @GetMapping("/")
    public ModelAndView getIndex() {
        return customModel.getCustomModelAndView("index");
    }

    @Override
    @GetMapping("/login")
    public ModelAndView getLogin() {
        return customModel.getCustomModelAndView("login");
    }

    @Override
    @GetMapping("/register")
    public ModelAndView getRegister(HttpServletRequest request) {
        return customModel.getCustomModelAndView("register");
    }

    @Override
    @PostMapping("/register")
    public ModelAndView postRegister(@Valid String email, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerInfo")
                .addObject("success",userService.registerEmail(email));
    }

    @Override
    @GetMapping("/register/{code}")
    public ModelAndView getSecondPartRegister(@PathVariable("code") String code, HttpServletRequest request) {
        return customModel.getCustomModelAndView("registerCode")
                .addObject("code",code);
    }

    @Override
    @PostMapping("/register/")
    public ModelAndView postSecondPartRegister(String code, String firstPass, String secondPass, HttpServletRequest request, String name, String lastName) {
        String info;
        if(userService.registerUser(firstPass, secondPass, name, lastName, code))
            info = "Uzytkownik zarejestrowany, można się zalogowac.";
        else
            info = "Zły kod";
        return customModel.getCustomModelAndView("info")
                .addObject("info",info);
    }

    @Override
    @GetMapping("/logout")
    public ModelAndView getLogout(HttpServletRequest request) {
        return customModel.getCustomModelAndView("index");
    }

    /**********************************************************/

    @Override
    @GetMapping("/shop/cart/")
    public ModelAndView getCart() {
        Cart cart = userService.getCurrentUser().getCart();
        return customModel.getCustomModelAndView("shop/cart")
                .addObject("items", cart.getItems())
                .addObject("value", cart.getValue());
        //return CustomModel.getCustomModelAndView("shop/cart").addObject("items",userService.getCurrentUser().getCart().getItems()); //front do calculate himself
    }

    @Override
    @PostMapping("/shop/item/{itemId}")
    public ModelAndView addItemToCart(@PathVariable("itemId") int id, @Valid int amount, HttpServletRequest request) {
        if (amount != 0 && id != 0)
        itemService.addItemToCart(id,amount);
        Item item = itemService.getItemById(id);
        if (Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item", item);
    }

    @Override
    @GetMapping("/shop/{category}")
    public ModelAndView getByCategory(@PathVariable("category") String category) {
        return customModel.getCustomModelAndView("shop/category")
                .addObject("items",itemService.getLastsItemsByCategory(category,25));
    }

    @Override
    @GetMapping("/shop/item/{itemId}")
    public ModelAndView getItem(@PathVariable("itemId")String itemId) {
        Item item = itemService.getItemById(Integer.parseInt(itemId));
        if(Objects.isNull(item))
            return customModel.getCustomModelAndView("notFound");
        return customModel.getCustomModelAndView("shop/item")
                .addObject("item",item);
    }

    @GetMapping("/admin/logs")
    @Override
    public ModelAndView getLogs() {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs",logService.getAllLogs());
    }

    @PostMapping("/admin/logs")
    @Override
    public ModelAndView getLogsWithFilter(@Valid String ip, @Valid String secondIp, @Valid String type) {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs", logService.getLogsWithFilters(ip, secondIp, type));
    }

    @Override
    @GetMapping("/admin/users")
    public ModelAndView getUsers() {
        return customModel.getCustomModelAndView("admin/users")
                .addObject("users",userService.getAllUsers());
    }

    @GetMapping("/admin/users/{id}")
    @Override
    public ModelAndView getUser(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/user")
                .addObject("user",userService.findById(id).get());
    }

    @GetMapping("/admin/items")
    @Override
    public ModelAndView getItems() {
        return customModel.getCustomModelAndView("admin/items")
                .addObject("items",itemService.getAllItems());
    }

    @GetMapping("/admin/items/{id}")
    @Override
    public ModelAndView getItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/item")
                .addObject("item",itemService.getItemById(id));
    }

    @GetMapping("/admin/new-item")
    @Override
    public ModelAndView getAddNewItem() {
        return customModel.getCustomModelAndView("admin/newItem").addObject("categoryList", itemService.getAllCategory())
                .addObject("item", new Item())
                .addObject("category", new Category());
    }

    @PostMapping("/admin/new-item")
    @Override
    public ModelAndView postAddNewItem(@Valid Item item, @Valid String cat, HttpServletRequest request,@Valid MultipartFile myFile) {
        itemService.saveItem(item, cat,myFile);
        return customModel.getCustomModelAndView("info")
                .addObject("info","Item zostal dodany!");
    }

    @GetMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editGetItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item", itemService.getItemById(id))
                .addObject("categoryList", itemService.getAllCategory());
    }

    @PostMapping("/admin/item/edit/{id}")
    @Override
    public ModelAndView editPostItem(@Valid Item item, @Valid String cat,HttpServletRequest request,MultipartFile myFile) {
        itemService.saveItem(item,cat,myFile);
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item",item)
                .addObject("info","Item został zmieniony")
                .addObject("categoryList", itemService.getAllCategory());
    }

    @PostMapping("/admin/item/delete/{id}")
    @Override
    public ModelAndView removeItem(@PathVariable("id") int id,HttpServletRequest request) {
        itemService.deleteItemById(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item zostal usunięty!");
    }

    @GetMapping("/admin/shipping-option")
    @Override
    public ModelAndView getShippingOption() {
        return customModel.getCustomModelAndView("admin/shippingOptions")
                .addObject("shippingList", shippingOptionService.findAll());
    }

    @GetMapping("/admin/shipping-option-new")
    @Override
    public ModelAndView getAddShippingOption() {
        return customModel.getCustomModelAndView("admin/newShippingOption")
                .addObject("shipping", new ShippingOption());
    }

    @PostMapping("/admin/shipping-option-new")
    @Override
    public ModelAndView postAddShippingOption(@Valid ShippingOption shipping,HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been created.");
    }

    @GetMapping("/admin/shipping-option/{id}")
    @Override
    public ModelAndView editGetShippingOption(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/shippingOptionEdit")
                .addObject("shipping", shippingOptionService.findById(id));
    }

    @GetMapping("/admin/shipping-option-del/{id}")
    @Override
    public ModelAndView removeShippingOption(@PathVariable("id") int id,HttpServletRequest request) {
        shippingOptionService.delete(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been deleted.");
    }

    @PostMapping("/admin/shipping-option/{}")
    @Override
    public ModelAndView editPostShippingOption(ShippingOption shipping,HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been changed.");
    }


    @GetMapping("/settings")
    @Override
    public ModelAndView userSettings() {
        return customModel.getCustomModelAndView("user/settings");
    }

    @PostMapping("/settings")
    @Override
    public ModelAndView userSettingsChange(@Valid String name, @Valid String lastName, @Valid String password, @Valid String repeatPassword, @Valid String oldPassword) {
        return customModel.getCustomModelAndView("user/settings")
                .addObject("message", userService.changeUser(name, lastName, password, repeatPassword, oldPassword));
    }

    @GetMapping("/settings/history")
    @Override
    public ModelAndView userHistory() {
        return customModel.getCustomModelAndView("user/history")
                .addObject("orders",userService.getCurrentUser().getOrders());
    }

    @GetMapping("/settings/security")
    @Override
    public ModelAndView userSecurity() {
        return customModel.getCustomModelAndView("user/security")
                .addObject("logs", userService.getCurrentUser().getLogs());
    }

    @GetMapping("/settings/address")
    @Override
    public ModelAndView userGetAddress() {
        return customModel.getCustomModelAndView("user/address")
                .addObject("address", userService.getCurrentUser().getAdress());
    }

    @PostMapping("/settings/address")
    @Override
    public ModelAndView userPostAddress(@Valid Adress adress) {
        addressService.save(adress);
        return customModel.getCustomModelAndView("user/address")
                .addObject("address", userService.getCurrentUser().getAdress());
    }

    @GetMapping("/shop/cart/buy")
    //@Override
    public ModelAndView userBuyGet() {
        User currentUser = userService.getCurrentUser();
        return customModel.getCustomModelAndView("shop/buy")
                .addObject("items", currentUser.getCart().getItems())
                .addObject("shippingList",shippingOptionService.findAll())
                .addObject("address",currentUser.getAdress())
                .addObject("value", currentUser.getCart().getValue());
    }

    @PostMapping("/shop/cart/buy")
    //@Override
    public ModelAndView userBuyPost(@Valid int shipping,@Valid Adress address) {
        userService.buyCart(shipping,address);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item has been bought");
    }


    @GetMapping("/admin/order")
    @Override
    public ModelAndView ordersGet(HttpServletRequest request) {
        return customModel.getCustomModelAndView("admin/orders")
                .addObject("orders", orderService.getAll());
    }

    @GetMapping("/admin/order/{id}")
    @Override
    public ModelAndView ordersGet(@PathVariable("id") int id, HttpServletRequest request) {
        return customModel.getCustomModelAndView("admin/order")
                .addObject("order", orderService.getById(id))
                .addObject("orderStatusList",orderStatusService.getAll());
    }

    @PostMapping("/admin/order/{id}")
    public ModelAndView ordersUpdate(@PathVariable("id") int id,@Valid int statusId, HttpServletRequest request) {
        orderService.update(id,statusId);
        return customModel.getCustomModelAndView("admin/order")
                .addObject("order", orderService.getById(id))
                .addObject("orderStatusList",orderStatusService.getAll());
    }

    @Override
    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        return customModel.getCustomModelAndView("accessDenied");
    }

    @GetMapping("/image/{id}")
    public Object image(@PathVariable("id") int id){
        byte[] image = itemService.getItemById(id).getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @GetMapping("/static/favicon.ico")
    public Object favicon(){
        URL imageUrl = getClass().getResource("/static/favicon.ico");
        try {
            return Image.getInstance(imageUrl).getOriginalData();
        }catch (Exception e){

        }
        return null;
    }
}