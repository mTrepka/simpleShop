package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.Cart;
import com.mTrepka.simpleShop.domain.Role;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.repository.RoleRepository;
import com.mTrepka.simpleShop.repository.UserRepository;
import com.mTrepka.simpleShop.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User getCurrentUser() {
        return findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public User findUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    @Override
    public boolean validateEmail(String email) {
        return Objects.isNull(userRepository.findUserByEmail(email));
    }

    @Override
    public boolean registerEmail(String email) {
        if(validateEmail(email)){
            User newUser = new User();
            String code = new RandomString(64, ThreadLocalRandom.current()).nextString();
            newUser.setCode(code);
            newUser.setEmail(email);
            userRepository.saveAndFlush(newUser);
            emailSender.sendRegisterCode(code,email);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerUser(String firstPass, String secondPass, String name, String lastName,String code) {
        User newUser = userRepository.findUserByCode(code);
        if(validateUser(firstPass,secondPass )&& Objects.nonNull(newUser)){
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            newUser.setPassword(encoder.encode(secondPass));
            newUser.setCode(null);
            Role role = roleRepository.findByRole("USER");
            newUser.setRoles(new HashSet<>(Arrays.asList(role)));
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setActive(true);
            userRepository.save(newUser);
            newUser.setCart(new Cart());
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByCode(String code) {
        return userRepository.findUserByCode(code);
    }

    @Override
    public boolean validateUser(String firstPass, String secondPass) {
        return firstPass.equals(secondPass);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
