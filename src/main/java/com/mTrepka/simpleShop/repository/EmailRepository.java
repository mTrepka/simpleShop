package com.mTrepka.simpleShop.repository;


import com.mTrepka.simpleShop.domain.EmailMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository  extends JpaRepository<EmailMap,Integer> {
    EmailMap getById(int id);
    EmailMap findByEmail(String email);
}
