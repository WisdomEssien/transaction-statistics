package com.seerbit.transactionstatistics.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class AppUtil {
    private AppUtil(){}

    private static final AtomicLong counter = new AtomicLong(1);
    public static long generateId(){
        return counter.incrementAndGet();
    }

    public static LocalDateTime convertToLocalDatetime(String input){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(input, DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault()));
        return zonedDateTime.toLocalDateTime();
    }
}
