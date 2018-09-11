package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.EmailMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface EmailService {
    EmailMap getById(int id);
}
