package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.ShippingOption;

import java.util.List;

public interface ShippingOptionService {
    List<ShippingOption> findAll();

    ShippingOption findById(int id);

    void save(ShippingOption shippingOption);

    void delete(int id);
}
