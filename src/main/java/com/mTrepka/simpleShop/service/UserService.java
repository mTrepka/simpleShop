package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.User;

public interface UserService {
    public User getCurrentUser();
    public User findUserByEmail(String email);
    boolean vaildUser(User user);
}
