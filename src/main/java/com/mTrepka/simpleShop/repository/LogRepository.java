package com.mTrepka.simpleShop.repository;

import com.mTrepka.simpleShop.domain.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<UserLog,Integer> {
    List<UserLog> findAllByIp(String ip);
    List<UserLog> findAllBySecondIp(String secondIp);
    List<UserLog> findAllByType(String type);
}
