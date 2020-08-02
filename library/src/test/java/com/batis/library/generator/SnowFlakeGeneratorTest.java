package com.batis.library.generator;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SnowFlakeGeneratorTest {

    @Test
    void nextId() {
        LocalDateTime dateTime = LocalDateTime.of(2020,6,1,0,0,0);
        ZonedDateTime zdt = dateTime.atZone(ZoneId.of("GMT"));
//        System.out.println(zdt.toInstant().toEpochMilli());
//        System.out.println(Instant.now().toEpochMilli());
    }
}