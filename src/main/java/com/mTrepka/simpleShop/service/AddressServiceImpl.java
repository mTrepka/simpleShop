package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.Adress;
import com.mTrepka.simpleShop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public void save(Adress a) {
        addressRepository.saveAndFlush(a);
    }

}
