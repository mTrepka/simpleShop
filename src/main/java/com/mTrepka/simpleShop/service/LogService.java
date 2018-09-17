package com.mTrepka.simpleShop.service;

import com.mTrepka.simpleShop.domain.UserLog;

import java.util.List;

public interface LogService {
    void add(UserLog userLog);
    List<UserLog> getAllLogs();
    List<UserLog> getLogsByType(String type);
    List<UserLog> getLogsByIp(String ip);
    List<UserLog> getLogsBySecondIp(String ip);

    List<UserLog> getLogsWithFilters(String ip, String secondIp, String type);

}
