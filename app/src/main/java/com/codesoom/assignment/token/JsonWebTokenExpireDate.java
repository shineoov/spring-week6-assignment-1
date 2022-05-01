package com.codesoom.assignment.token;

import java.util.Date;

/**
 * JWT 만료 시간
 */
public class JsonWebTokenExpireDate {

    private final Date jwtExpireDate;

    public JsonWebTokenExpireDate(Integer hour, Integer minute, Integer second) {

        Date nowDate = new Date();

        long plusExpireTime = 0;
        if(hour != null) {
            plusExpireTime += 1000L * 60 * 60 * hour;
        }

        if(minute != null) {
            plusExpireTime += 1000L * 60 * minute;
        }

        if(second != null) {
            plusExpireTime += 1000L * second;
        }

        this.jwtExpireDate = new Date(nowDate.getTime() + plusExpireTime);
    }

    public Date getJwtExpireDate() {
        return jwtExpireDate;
    }
}
