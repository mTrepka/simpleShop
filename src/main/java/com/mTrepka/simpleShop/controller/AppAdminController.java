package com.mTrepka.simpleShop.controller;

import com.mTrepka.simpleShop.domain.shop.Category;
import com.mTrepka.simpleShop.domain.shop.Item;
import com.mTrepka.simpleShop.domain.shop.ShippingOption;
import com.mTrepka.simpleShop.service.LogService;
import com.mTrepka.simpleShop.service.UserService;
import com.mTrepka.simpleShop.service.shop.*;
import com.mTrepka.simpleShop.utility.CustomModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController("/admin")
@EnableAspectJAutoProxy
@AllArgsConstructor
public class AppAdminController implements AppAdmin {
    private final LogService logService;
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final CategoryService categoryService;
    private final CustomModel customModel;
    private final UserService userService;
    private final ItemService itemService;
    private final ShippingOptionService shippingOptionService;

    @Override
    public ModelAndView getLogs() {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs", logService.getAllLogs());
    }


    @Override
    public ModelAndView getLogsWithFilter(@Valid String ip, @Valid String secondIp, @Valid String type) {
        return customModel.getCustomModelAndView("admin/logs")
                .addObject("logs", logService.getLogsWithFilters(ip, secondIp, type));
    }

    @Override
    public ModelAndView getUsers() {
        return customModel.getCustomModelAndView("admin/users")
                .addObject("users", userService.getAllUsers());
    }


    @Override
    public ModelAndView getUser(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/user")
                .addObject("user", userService.findById(id).get());
    }


    @Override
    public ModelAndView getItems() {
        return customModel.getCustomModelAndView("admin/items")
                .addObject("items", itemService.getAllItems());
    }


    @Override
    public ModelAndView getItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/item")
                .addObject("item", itemService.getItemById(id));
    }


    @Override
    public ModelAndView getAddNewItem() {
        return customModel.getCustomModelAndView("admin/newItem").addObject("categoryList", itemService.getAllCategory())
                .addObject("item", new Item())
                .addObject("category", new Category());
    }


    @Override
    public ModelAndView postAddNewItem(@Valid Item item, @Valid String cat, HttpServletRequest request, @Valid MultipartFile myFile) {
        itemService.saveItem(item, cat, myFile);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item zostal dodany!");
    }

    @Override
    public ModelAndView editGetItem(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item", itemService.getItemById(id))
                .addObject("categoryList", itemService.getAllCategory());
    }

    @Override
    public ModelAndView editPostItem(@Valid Item item, @Valid String cat, HttpServletRequest request, MultipartFile myFile) {
        itemService.saveItem(item, cat, myFile);
        return customModel.getCustomModelAndView("admin/itemEdit")
                .addObject("item", item)
                .addObject("info", "Item został zmieniony")
                .addObject("categoryList", itemService.getAllCategory());
    }

    @Override
    public ModelAndView removeItem(@PathVariable("id") int id, HttpServletRequest request) {
        itemService.deleteItemById(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Item zostal usunięty!");
    }

    @Override
    public ModelAndView getShippingOption() {
        return customModel.getCustomModelAndView("admin/shippingOptions")
                .addObject("shippingList", shippingOptionService.findAll());
    }

    @Override
    public ModelAndView getAddShippingOption() {
        return customModel.getCustomModelAndView("admin/newShippingOption")
                .addObject("shipping", new ShippingOption());
    }

    @Override
    public ModelAndView postAddShippingOption(@Valid ShippingOption shipping, HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been created.");
    }

    @Override
    public ModelAndView editGetShippingOption(@PathVariable("id") int id) {
        return customModel.getCustomModelAndView("admin/shippingOptionEdit")
                .addObject("shipping", shippingOptionService.findById(id));
    }

    @Override
    public ModelAndView removeShippingOption(@PathVariable("id") int id, HttpServletRequest request) {
        shippingOptionService.delete(id);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been deleted.");
    }

    @Override
    public ModelAndView editPostShippingOption(ShippingOption shipping, HttpServletRequest request) {
        shippingOptionService.save(shipping);
        return customModel.getCustomModelAndView("info")
                .addObject("info", "Shipping option has been changed.");
    }

    @Override
    public ModelAndView getCategories() {
        return customModel.getCustomModelAndView("admin/categories")
                .addObject("categories", categoryService.getAll());
    }

    @Override
    public ModelAndView getNewCategory() {
        return customModel.getCustomModelAndView("admin/newCategory")
                .addObject("categories", categoryService.getAll());
    }

    @Override
    public ModelAndView postNewCategory(String name, Integer parentId) {
        int parent = parentId == null ? 0 : parentId.intValue();
        categoryService.newCategory(name, parent);
        return customModel.getCustomModelAndView("admin/categories")
                .addObject("categories", categoryService.getAll());
    }

    @Override
    public ModelAndView getDeleteCategory(@PathVariable("id") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return customModel.getCustomModelAndView("admin/categories")
                .addObject("categories", categoryService.getAll());
    }

    @Override
    public ModelAndView postEditCategory(@PathVariable("id") int categoryId, String name, int parentId) {
        categoryService.editAndSave(categoryId, name, parentId);
        return customModel.getCustomModelAndView("admin/newCategory")
                .addObject("categories", categoryService.getAll());
    }

    @Override
    public ModelAndView getEditCategory(@PathVariable("id") int categoryId) {
        return customModel.getCustomModelAndView("admin/edit-category")
                .addObject("category", categoryService.getById(categoryId))
                .addObject("allCategories", categoryService.getAll());
    }

    @Override
    public ModelAndView ordersGet(HttpServletRequest request) {
        return customModel.getCustomModelAndView("admin/orders")
                .addObject("orders", orderService.getAll());
    }

    @Override
    public ModelAndView ordersGet(@PathVariable("id") int id, HttpServletRequest request) {
        return customModel.getCustomModelAndView("admin/order")
                .addObject("order", orderService.getById(id))
                .addObject("orderStatusList", orderStatusService.getAll());
    }

    @Override
    public ModelAndView ordersUpdate(@PathVariable("id") int id, @Valid int statusId, HttpServletRequest request) {
        orderService.update(id, statusId);
        return customModel.getCustomModelAndView("admin/order")
                .addObject("order", orderService.getById(id))
                .addObject("orderStatusList", orderStatusService.getAll());
    }
}
