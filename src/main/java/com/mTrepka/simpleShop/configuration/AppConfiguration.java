package com.mTrepka.simpleShop.configuration;


import com.mTrepka.simpleShop.dataAspect.ItemAspectController;
import com.mTrepka.simpleShop.dataAspect.RegistrationAspectController;
import com.mTrepka.simpleShop.dataAspect.ShippingOptionAspectController;
import com.mTrepka.simpleShop.dataAspect.UserAspectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
public class AppConfiguration {
    @Value("${app.mail.username}")
    private String username;
    @Value("${app.mail.password}")
    private String password;

    @Bean(name = "mailSender")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("");
        mailSender.setPassword("");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    UserAspectController userAspectController() {
        return new UserAspectController();
    }

    @Bean
    RegistrationAspectController registrationAspectController() {
        return new RegistrationAspectController();
    }
    @Bean
    ShippingOptionAspectController shippingOptionAspectController() {return new ShippingOptionAspectController();}
    @Bean
    ItemAspectController itemAspectController() {return new ItemAspectController();}
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Resource(name = "customUserDetailsService")
    private CustomUserDetailsService customUserDetailsService;
}
