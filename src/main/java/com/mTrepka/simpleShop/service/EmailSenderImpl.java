package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.EmailTemplate;
import com.mTrepka.simpleShop.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("emailSender")
public class EmailSenderImpl implements EmailSender {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private MailSender mailSender;
    @Override
    public void sendRegisterCode(String code, String email) {
        EmailTemplate template = emailTemplateRepository.findByName("email");
        String result = template.getTemplate();
        HashMap<String, String> dataMap = template.getDataMap();
        result.replace(dataMap.get("code"), code);
        result.replace(dataMap.get("email"), email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setTo(dataMap.get("title"));
        message.setText(result);
        mailSender.send(message);
    }
}
