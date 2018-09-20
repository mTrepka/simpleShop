package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.ShippingOption;
import com.mTrepka.simpleShop.repository.ShippingOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shippingOptionService")
public class ShippingOptionServiceImpl implements ShippingOptionService {
    @Autowired
    private ShippingOptionRepository shippingOptionRepository;

    @Override
    public List<ShippingOption> findAll() {
        return shippingOptionRepository.findAll();
    }

    @Override
    public ShippingOption findById(int id) {
        return shippingOptionRepository.getOne(id);
    }

    @Override
    public void save(ShippingOption shippingOption) {
        shippingOptionRepository.save(shippingOption);
    }

    @Override
    public void delete(int id) {
        shippingOptionRepository.deleteById(id);
    }
}
