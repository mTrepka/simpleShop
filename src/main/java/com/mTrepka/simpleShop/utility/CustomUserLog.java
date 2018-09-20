package com.mTrepka.simpleShop.utility;

import com.mTrepka.simpleShop.domain.UserLog;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomUserLog
{
    public static UserLog getCustomUserLog(HttpServletRequest request){
        UserLog log = new UserLog();
        log.setData(Timestamp.valueOf(LocalDateTime.now()));
        log.setIp(request.getRemoteAddr());
        log.setSecondIp(request.getLocalAddr());
        return log;
    }
}
