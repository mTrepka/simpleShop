package com.mTrepka.simpleShop.service;

import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSenderImpl implements EmailSender {
    @Override
    public void sendRegisterCode(String code, String email) {
        System.out.println("to implementation");
    }
}
