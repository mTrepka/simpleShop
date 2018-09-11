package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements LogService{
    @Autowired
    private LogRepository logRepository;

    @Override
    public void add(UserLog userLog) {
        logRepository.save(userLog);
    }
}
