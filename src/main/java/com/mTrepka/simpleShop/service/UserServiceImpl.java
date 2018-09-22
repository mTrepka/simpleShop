package com.mTrepka.simpleShop.service;


import com.mTrepka.simpleShop.domain.*;
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
    @Autowired
    private CartService cartService;
    @Autowired
    private ShippingService shippingService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShippingOptionService shippingOptionService;
    @Autowired
    private OrderService orderService;

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
            Cart cart = new Cart();
            cart.setUser(newUser);
            Adress adress = new Adress();
            adress.setUser(newUser);
            addressService.save(adress);
            newUser.setAdress(adress);
            cartService.save(cart);
            newUser.setCart(cart);
            userRepository.save(newUser);
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

    @Override
    public String changeUser(String name, String lastName, String password, String repeatPassword, String oldPassword) {
        return null;
    }

    @Override
    public void save(User u) {
        userRepository.save(u);
    }

    @Override
    public void buyCart(int shipping, Adress address) {
        User current = getCurrentUser();

        Order order = new Order();
        order.setCart(current.getCart());

        Cart newCart = new Cart();
        cartService.save(newCart);
        current.setCart(newCart);

        Shipping shipp = new Shipping();
        if(current.equals(address))
        {shipp.setAdress(current.getAdress());}
        else{
            addressService.save(address);
            shipp.setAdress(address);
        }
        shipp.setOption(shippingOptionService.findById(shipping));
        shippingService.save(shipp);

        order.setShipping(shipp);
        orderService.save(order);
        current.getOrders().add(order);
        userRepository.save(current);
    }
}
