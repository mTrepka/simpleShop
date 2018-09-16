package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.UserLog;
import com.mTrepka.simpleShop.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("logService")
public class LogServiceImpl implements LogService{
    @Autowired
    private LogRepository logRepository;

    @Override
    public void add(UserLog userLog) {
        logRepository.save(userLog);
    }

    @Override
    public List<UserLog> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public List<UserLog> getLogsByIp(String ip) {
        return logRepository.findAllByIp(ip);
    }

    @Override
    public List<UserLog> getLogsBySecondIp(String ip) {
        return  logRepository.findAllBySecondIp(ip);
    }

    @Override
    public List<UserLog> getLogsByType(String type) {
        return logRepository.findAllByType(type);
    }

    @Override
    public List<UserLog> getLogsWithFilters(String ip, String secondIp, String type) {
        List<UserLog> logs = this.getAllLogs();
        Stream<UserLog> stream = logs.stream();
        if(Objects.nonNull(ip))
            stream = stream.filter(e -> e.getIp().equals(ip));
        if(Objects.nonNull(secondIp))
            stream = stream.filter(e -> e.getSecondIp().equals(secondIp));
        if(Objects.nonNull(type))
            stream = stream.filter(e -> e.getType().equals(type));
        return stream.collect(Collectors.toList());
    }
}
