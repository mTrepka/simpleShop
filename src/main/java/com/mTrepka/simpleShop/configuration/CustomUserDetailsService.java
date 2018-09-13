package com.mTrepka.simpleShop.configuration;

import com.mTrepka.simpleShop.domain.Role;
import com.mTrepka.simpleShop.domain.User;
import com.mTrepka.simpleShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional()
    public UserDetails loadUserByUsername(String nick)
            throws UsernameNotFoundException {
        User user = userService.findUserByEmail(nick);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isActive(), true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role userProfile : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRole()));
        }
        return authorities;
    }
}
