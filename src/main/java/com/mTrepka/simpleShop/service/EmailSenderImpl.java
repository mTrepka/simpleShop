package com.mTrepka.simpleShop.service;

import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSenderImpl implements EmailSenderService{
    @Override
    public void sendRegisterCode(String code, String email) {

    }
}
