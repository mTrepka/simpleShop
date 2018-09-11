package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService{
    User getCurrentUser();
    User findUserByEmail(String email);
    boolean validateEmail(String email);
    boolean registerEmail(String email);
    Optional<User> findById(int id);
    //String validateUser(String firstPass, String secondPass, String name, String lastName);
    //String registerUser(String firstPass, String secondPass, String name, String lastName);
}
