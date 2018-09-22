package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.Adress;
import com.mTrepka.simpleShop.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    User getCurrentUser();
    User findUserByEmail(String email);
    boolean validateEmail(String email);
    boolean registerEmail(String email);
    Optional<User> findById(int id);
    boolean validateUser(String firstPass, String secondPass);
    boolean registerUser(String firstPass, String secondPass, String name, String lastName,String code);

    User findUserByCode(String code);

    List<User> getAllUsers();

    String changeUser(String name, String lastName, String password, String repeatPassword, String oldPassword);
    void save(User u);

    void buyCart(int shipping, Adress address);
}
