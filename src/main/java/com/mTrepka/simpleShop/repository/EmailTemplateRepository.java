package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    EmailTemplate findByName(String name);
}
