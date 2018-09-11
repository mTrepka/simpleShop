package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.EmailMap;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.repository.EmailRepository;
import com.mTrepka.simpleShop.repository.UserRepository;
import com.mTrepka.simpleShop.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmailSenderService emailSender;
    @Override
    public User getCurrentUser() {
        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public User findUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    @Override
    public boolean validateEmail(String email) {
        return Objects.isNull(emailRepository.findByEmail(email));
    }

    @Override
    public boolean registerEmail(String email) {
        if(validateEmail(email)){
            EmailMap emailMap = new EmailMap();
            String code = new RandomString(20, ThreadLocalRandom.current()).nextString();
            emailMap.setCode(code);
            emailMap.setEmail(email);
            emailRepository.save(emailMap);
            emailSender.sendRegisterCode(code,email);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
