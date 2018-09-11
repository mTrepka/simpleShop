package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<UserLog,Integer> {
}
