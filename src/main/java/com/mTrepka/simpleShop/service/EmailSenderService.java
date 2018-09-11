package com.mTrepka.simpleShop.service;

public interface EmailSenderService {
    void sendRegisterCode(String code,String email);
}
