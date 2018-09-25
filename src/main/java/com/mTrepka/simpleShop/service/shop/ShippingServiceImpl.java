package com.mTrepka.simpleShop.service.shop;

import com.mTrepka.simpleShop.domain.shop.Shipping;
import com.mTrepka.simpleShop.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService{
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public void save(Shipping s) {
        shippingRepository.save(s);
    }
}
