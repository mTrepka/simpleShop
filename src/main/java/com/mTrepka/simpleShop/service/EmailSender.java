package com.mTrepka.simpleShop.service;

public interface EmailSender {
    void sendRegisterCode(String code,String email);
}
